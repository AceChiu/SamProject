<template>
  <div id="app">
    <header>
      <h1>Friendship App</h1>
    </header>
    <main>
      <section v-if="!authenticated">
        <h2>Login to Connect with Friends</h2>
        <button @click="loginWithGoogle">Login with Google</button>
      </section>
      <section v-else>
        <h2>Welcome, {{ userProfile.name }}!</h2>
        <!-- Display user information or allow them to upload data -->
      </section>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      authenticated: false,
      userProfile: null,
    };
  },
  methods: {
    async loginWithGoogle() {
      try {
        await this.initGoogleAuth();
        const authInstance = gapi.auth2.getAuthInstance();

        // Use the sign in method
        const googleUser = await authInstance.signIn();

        // Get user profile information
        const userProfile = googleUser.getBasicProfile();
        
        this.authenticated = true;
        this.userProfile = {
          id: userProfile.getId(),
          name: userProfile.getName(),
          email: userProfile.getEmail(),
          imageUrl: userProfile.getImageUrl(),
        };

        // You can now perform additional actions, like sending the user profile to your server.
      } catch (error) {
        console.error('Error during Google login:', error);
      }
    },
    async initGoogleAuth() {
      return new Promise((resolve, reject) => {
        gapi.load('auth2', async () => {
          try {
            await gapi.auth2.init({
              client_id: 'YOUR_CLIENT_ID', // Replace with your actual client ID
            });
            resolve();
          } catch (error) {
            reject(error);
          }
        });
      });
    },
  },
};
</script>

<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  #app {
    text-align: center;
    margin-top: 50px;
  }

  header {
    background-color: #3498db;
    padding: 20px;
    color: white;
  }

  main {
    margin: 20px;
  }

  button {
    padding: 10px 20px;
    font-size: 1rem;
    background-color: #2ecc71;
    color: white;
    border: none;
    cursor: pointer;
  }

  button:hover {
    background-color: #27ae60;
  }
</style>
