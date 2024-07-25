<template>
  <div class="default-header">
    <div class="default-header__left">
      <NavigationHeader></NavigationHeader>
      <a
        class="default-header__left-title default-header__center-title"
        @click="$router.push({ name: 'home' })"
      >
      </a>
    </div>

    <div class="default-header__right">
      <!-- <DropdownLocal></DropdownLocal> -->
      <DropdownUser></DropdownUser>
    </div>
  </div>
</template>
<script setup lang="ts">
// import DropdownLocal from '~/components/layouts/DropdownLocal.vue'
import DropdownUser from '~/components/layouts/DropdownUser.vue'
import NavigationHeader from './NavigationHeader.vue'
</script>
<style lang="scss" scoped>
.default-header {
  margin-left: -20px;
  height: 100%;
  color: black;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  overflow: hidden;
  &__left {
    display: flex;
    align-items: center;
  }
  &__right {
    display: flex;
    align-items: center;
  }
  &__left-title {
    display: inline-flex;
    align-items: baseline;
    color: black;
    font-size: 16px;
    font-weight: 600;
    text-decoration: none;
    white-space: nowrap;
    cursor: pointer;
  }
  &__center-title {
    @media (max-width: 767.98px) {
      margin-top: 0;
      cursor: pointer;
    }
  }
  &__logo {
    margin-right: 16px;
  }
  &__title {
    @media (max-width: 767.98px) {
      display: none;
    }
  }
  .dropdown-local {
    margin-right: 16px;
  }
  &__hamburger {
    margin-right: 20px;
    cursor: pointer;
    z-index: 100;
    &--close {
      color: #fff;
      @media (max-width: 767.98px) {
        color: $delta-blue;
      }
    }
  }
}
</style>
