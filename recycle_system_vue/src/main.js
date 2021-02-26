// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import './plugins/axios.js'
import './plugins/element.js'
import  VueParticles  from   'vue-particles'
import id from 'element-ui/src/locale/lang/id'

Vue.config.productionTip = false
Vue.use(VueParticles)

new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
router.beforeEach((to, from, next) => {
  let Token=sessionStorage.token
  let identity=sessionStorage.identity
  if (to.meta.needLogin) {
    if (Token) {
      next()
        if(to.meta.needUser){
            if(identity==1){
            }
            else next({path: '/404error'})
        }else if(to.meta.needCollector){
          if(identity==2){
          }
          else next({path:'/404error'})
        }else if(to.meta.needAdmin){
          if(identity==3){
          }
          else next({path:'/404error'})
        }
    }
    else next({path:'login'})
  } else {
    next()
  }
})
