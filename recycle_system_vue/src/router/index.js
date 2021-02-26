import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login'
import Index from '../views/index'
import UserLayout from '../views/User/userlayout'
import UserAllInorder from '../views/User/userallInorder'
import UserDoingInorder from '../views/User/userdoingInorder'
import Recycle from '../views/User/recycle'
import donate from '../views/User/donate'
import UserAlterInfo from '../views/User/useralterinfo'
import userupdateorder from '../views/User/userupdateorder'
import useralldonate from '../views/User/useralldonate'
import userdoingdonate from '../views/User/userdoingdonate'
import userecharts from '../views/User/userecharts'
import evaluate from '../views/User/evaluate'
import UserHomePage from '../views/User/userhomepage'
import CollectorLayout from '../views/Collector/collectorlayout'
import CollectorHomePage from '../views/Collector/collectorhomepage'
import CollectorAllInorder from '../views/Collector/collectorallInorder'
import CollectorDoingInorder from '../views/Collector/collectordoingInorder'
import CollectorAlterInfo from '../views/Collector/collectoralterinfo'
import collectoralldonate from '../views/Collector/collectoralldonate'
import collectordoingdonate from '../views/Collector/collectordoingdonate'
import confirmorder from '../views/Collector/confirmorder'
import collectorecharts from '../views/Collector/collectorecharts'
import NotFound from '../views/404'
import AdminLayout from '../views/Admin/adminlayout'
import ItemTypeManager from '../views/Admin/ItemTypeManager'
import ItemManager from '../views/Admin/ItemManager'
import adminallInorder from '../views/Admin/adminallInorder'
import admindoingInorder from '../views/Admin/admindoingInorder'
import adminalldonate from '../views/Admin/adminalldonate'
import admindoingdonate from '../views/Admin/admindoingdonate'
import HelpThePoorManager from '../views/Admin/HelpThePoorManager'
import AddHelpThePoor from '../views/Admin/AddHelpThePoor'
import adminCollectorManage from '../views/Admin/adminCollectorManage'
import adminUserManager from '../views/Admin/adminUserManager'
import adminecharts from '../views/Admin/adminecharts'
import adminhomepage from '../views/Admin/adminhomepage'
import userregister from '../views/userregister'
import collectorregister from '../views/collectorregister'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:"主页",
      component: Index,
      redirect:"/login"
    },
    {
      path:'/login',
      name:"登录页面",
      component: Login,
    },
    {
      path:'/userregister',
      component:userregister,
    },
    {
      path:'/collectorregister',
      component:collectorregister,
    },
    {
      path:'/404error',
      component:NotFound,
    },
    {
      path:'/adminlayout',
      component:AdminLayout,
      redirect:"/adminhomepage",
      meta: {
        needLogin: true, //需要登录
        needAdmin:true,
      },
      children:[
        {
          path:'/adminhomepage',
          component:adminhomepage,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminUserManager',
          component:adminUserManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/ItemTypeManage',
          component:ItemTypeManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/ItemManage',
          component:ItemManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminallinorder',
          component:adminallInorder,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/admindoinginorder',
          component:admindoingInorder,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminalldonate',
          component:adminalldonate,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/admindoingdonate',
          component:admindoingdonate,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminCollectorManage',
          component:adminCollectorManage,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/HelpThePoorManager',
          component:HelpThePoorManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/AddHelpThePoor',
          component:AddHelpThePoor,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminecharts',
          component:adminecharts,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
      ]
    },
    {
      path:'/collectorlayout',
      component:CollectorLayout,
      redirect:"collectorhomepage",
      meta: {
        needLogin: true, //需要登录
        needCollector:true,
      },
      children:[
        {
          path: '/collectorHomePage',
          component: CollectorHomePage,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectorallinorder',
          component: CollectorAllInorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/confirmorder',
          component: confirmorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectordoinginorder',
          component: CollectorDoingInorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectoralldonate',
          component:collectoralldonate,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectordoingdonate',
          component: collectordoingdonate,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectoralterinfo',
          component: CollectorAlterInfo,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectorecharts',
          component: collectorecharts,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
      ]
    },
    {
      path: '/userlayout',
      component: UserLayout,
      redirect:"/userhomepage",
      meta: {
        needLogin: true, //需要登录
        needUser:true,
      },
      children: [
        {
          path: '/userhomepage',
          component: UserHomePage,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },

        {
          path: '/userallinorder',
          component: UserAllInorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userdoinginorder',
          component: UserDoingInorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/useralldonate',
          component: useralldonate,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userdoingdonate',
          component: userdoingdonate,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/recycle',
          component: Recycle,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/evaluate',
          component: evaluate,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/donate',
          component: donate,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userupdateorder',
          component: userupdateorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/useralterinfo',
          component: UserAlterInfo,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userecharts',
          component: userecharts,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
      ]
    }

  ]
})
