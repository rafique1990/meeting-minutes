import Vue from 'vue';
import Vuex from 'vuex';

import { dataService } from '../shared';
import {
  ADD_PROJECT,
  DELETE_PROJECT,
  GET_PROJECTS,
  UPDATE_PROJECT,
  ADD_MEETING,
  DELETE_MEETING,
  GET_MEETINGS,
  UPDATE_MEETING,
} from './mutation-types';

Vue.use(Vuex);

const state = () => ({
  projects: [],
  meetings: [],
});

const mutations = {
  [ADD_PROJECT](state, project) {
    state.heroes.unshift(project); // mutable addition
  },
  [UPDATE_PROJECT](state, project) {
    const index = state.projects.findIndex(
      h => h.project_id === project.project_id
    );
    state.projects.splice(index, 1, project);
    state.projects = [...state.projects];
  },
  [GET_PROJECTS](state, projects) {
    state.projects = projects;
  },
  [DELETE_PROJECT](state, projectId) {
    state.projects = [
      ...state.projects.filter(p => p.project_id !== projectId),
    ];
  },
  [ADD_MEETING](state, meeting) {
    state.meetings.unshift(meeting); // mutable addition
  },
  [UPDATE_MEETING](state, meeting) {
    const index = state.meetings.findIndex(
      h => h.meeting_id === meeting.meeting_id
    );
    state.meetings.splice(index, 1, meeting);
    state.meetings = [...state.meetings];
  },
  [GET_MEETINGS](state, meetings) {
    state.meetings = meetings;
  },
  [DELETE_MEETING](state, meetingId) {
    state.meetings = [
      ...state.meetings.filter(p => p.meeting_id !== meetingId),
    ];
  },
};

const actions = {
  // actions let us get to ({ state, getters, commit, dispatch }) {
  async addProjectAction({ commit }, project) {
    const addedProject = await dataService.addProject(project);
    commit(ADD_PROJECT, addedProject);
  },
  async deleteProjectAction({ commit }, project) {
    const deletedProjectId = await dataService.deleteProject(project);
    commit(DELETE_PROJECT, deletedProjectId);
  },
  async getProjectsAction({ commit }) {
    const projects = await dataService.getProjects();
    commit(GET_PROJECTS, projects);
  },
  async updateProjectAction({ commit }, project) {
    const updatedProject = await dataService.updateProject(project);
    commit(UPDATE_PROJECT, updatedProject);
  },
  async addMeetingAction({ commit }, meeting) {
    const addedMeeting = await dataService.addMeeting(meeting);
    commit(ADD_MEETING, addedMeeting);
  },
  async deleteMeetingAction({ commit }, meeting) {
    const deletedMeetingId = await dataService.deleteMeeting(meeting);
    commit(DELETE_MEETING, deletedMeetingId);
  },
  async getMeetingsAction({ commit }) {
    const meetings = await dataService.getMeetings();
    commit(GET_MEETINGS, meetings);
  },
  async updateMeetingAction({ commit }, meeting) {
    const updatedMeeting = await dataService.updateMeeting(meeting);
    commit(UPDATE_MEETING, updatedMeeting);
  },
};

const getters = {
  // parameterized getters are not cached. so this is just a convenience to get the state.
  getProjectById: state => id => state.projects.find(h => h.project_id === id),
  getMeetingById: state => id => state.meetings.find(v => v.meeting_id === id),
};

export default new Vuex.Store({
  strict: process.env.NODE_ENV !== 'production',
  state,
  mutations,
  actions,
  getters,
});
