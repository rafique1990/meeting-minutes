import Vue from 'vue';
import Router from 'vue-router';
import PageNotFound from '@/views/page-not-found';

Vue.use(Router);

const parseProps = r => ({ id: parseInt(r.params.id) });
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/projects',
    },
    {
      path: '/projects',
      name: 'projects',
      component: () => import('./views/projects.vue'),
    },
    {
      path: '/projects/:id',
      name: 'project-detail',
      // props: true,
      props: parseProps,
      component: () => import('./views/project-detail.vue'),
    },
    {
      path: '/meetings',
      name: 'meetings',
      component: () => import('./views/meetings.vue'),
    },
    {
      path: '/meetings/:id',
      name: 'meeting-detail',
      // props: true,
      props: parseProps,
      component: () => import('./views/meeting-detail.vue'),
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('./views/about.vue'),
    },
    {
      path: '*',
      component: PageNotFound,
    },
  ],
});
