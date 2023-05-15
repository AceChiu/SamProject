import path from 'path'
import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import eslintPlugin from 'vite-plugin-eslint' // 新增這行
import Unocss from 'unocss/vite'
import {
  presetAttributify,
  presetIcons,
  presetUno,
  transformerDirectives,
  transformerVariantGroup
} from 'unocss'

const pathSrc = path.resolve(__dirname, 'src')


// https://vitejs.dev/config/
export default ({ mode }) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }
  console.log(process.env.NODE_ENV)
  return defineConfig({
    resolve: {
      alias: {
        '~/': `${pathSrc}/`
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: '@use "~/styles/element/index.scss" as *;'
        }
      }
    },
    plugins: [
      vue(),
      eslintPlugin({ cache: false }), // 新增這行
      Components({
        // allow auto load markdown components under `./src/components/`
        extensions: [
          '.mjs',
          '.js',
          '.ts',
          '.jsx',
          '.tsx',
          '.json',
          '.vue',
          '.scss',
          'vue',
          'md'
        ],
        // allow auto import and register components used in markdown
        include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
        resolvers: [
          ElementPlusResolver({
            importStyle: 'sass'
          })
        ],
        dts: 'src/components.d.ts'
      }),

      // https://github.com/antfu/unocss
      // see unocss.config.ts for config
      Unocss({
        presets: [
          presetUno(),
          presetAttributify(),
          presetIcons({
            scale: 1.2,
            warn: true
          })
        ],
        transformers: [transformerDirectives(), transformerVariantGroup()]
      })
    ],
    base: process.env.NODE_ENV === 'ghpage' ? 'https://github.deltaww.com/pages/ITMfgSuptDev/element-plus-vite-starter/' : ''
  })
}