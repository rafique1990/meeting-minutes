import * as axios from 'axios';
//import { format } from 'date-fns';
//import { inputDateFormat } from './constants';

import { API } from './config';

const getProjects = async function() {
  try {
    const response = await axios.get(`${API}/projects`);
    let data = parseList(response);
    return data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getProject = async function(id) {
  try {
    const response = await axios.get(`${API}/projects/${id}`);
    let project = parseItem(response, 200);
    return project;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const updateProject = async function(project) {
  try {
    const response = await axios.put(`${API}/projects/${project.project_id}`, project);
    const updatedProject = parseItem(response, 200);
    return updatedProject;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const addProject = async function(project) {
  try {
    const response = await axios.post(`${API}/projects`, project);
    const addedProject = parseItem(response, 201);
    return addedProject;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const deleteProject = async function(project) {
  try {
    const response = await axios.delete(`${API}/projects/${project.project_id}`);
    parseItem(response, 200);
    return project.project_id;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const getMeetings = async function() {
  try {
    const response = await axios.get(`${API}/meetings`);
    let data = parseList(response);
    return data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getMeeting = async function(id) {
  try {
    const response = await axios.get(`${API}/meetings/${id}`);
    let meeting = parseItem(response, 200);
    return meeting;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const updateMeeting = async function(meeting) {
  try {
    const response = await axios.put(`${API}/meetings/${meeting.meeting_id}`, meeting);
    const updatedMeeting = parseItem(response, 200);
    return updatedMeeting;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const addMeeting = async function(meeting) {
  try {
    const response = await axios.post(`${API}/meetings`, meeting);
    const addedMeeting = parseItem(response, 201);
    return addedMeeting;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const deleteMeeting = async function(meeting) {
  try {
    const response = await axios.delete(`${API}/meetings/${meeting.meeting_id}`);
    parseItem(response, 200);
    return meeting.meeting_id;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const parseList = response => {
  if (response.status !== 200) throw Error(response.message);
  if (!response.data) return [];
  let list = response.data;
  if (typeof list !== 'object') {
    list = [];
  }
  return list;
};

export const parseItem = (response, code) => {
  if (response.status !== code) throw Error(response.message);
  let item = response.data;
  if (typeof item !== 'object') {
    item = undefined;
  }
  return item;
};

export const dataService = {
  getProjects,
  getProject,
  updateProject,
  addProject,
  deleteProject,
  getMeetings,
  getMeeting,
  updateMeeting,
  addMeeting,
  deleteMeeting,
};
