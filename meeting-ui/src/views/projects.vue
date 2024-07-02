<template>
  <div class="content-container">
    <div class="columns">
      <div class="column is-8">
        <div class="section content-title-group">
          <h2 class="title">Projects</h2>
          <button class="button refresh-button" @click="loadProjects()">
            <i class="fas fa-sync"></i>
          </button>
          <router-link
            tag="button"
            class="button add-button"
            :to="{ name: 'project-detail', params: { id: 0 } }"
          >
            <i class="fas fa-plus"></i>
          </router-link>
          <ul>
            <li v-for="project in projects" :key="project.project_id">
              <div class="card">
                <div class="card-content">
                  <div class="content">
                    <div :key="project.project_name" class="name">
                      {{ project.project_name }}
                    </div>
                    <div class="description">{{ project.active }}</div>
                  </div>
                </div>
                <footer class="card-footer">
                  <button
                    class="link card-footer-item"
                    @click="askToDelete(project)"
                  >
                    <i class="fas fa-trash"></i>
                    <span>Delete</span>
                  </button>
                  <router-link
                    tag="button"
                    class="link card-footer-item"
                    :to="{
                      name: 'project-detail',
                      params: { id: project.project_id },
                    }"
                  >
                    <i class="fas fa-check"></i>
                    <span>Select</span>
                  </router-link>
                </footer>
              </div>
            </li>
          </ul>
        </div>
        <div class="notification is-info" v-show="message">{{ message }}</div>
      </div>
    </div>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteProject"
    >
    </Modal>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Modal from '@/components/modal';

export default {
  name: 'projects',
  data() {
    return {
      projectToDelete: null,
      message: '',
      showModal: false,
    };
  },
  components: {
    Modal,
  },
  async created() {
    await this.loadProjects();
  },
  methods: {
    ...mapActions(['getProjectsAction', 'deleteProjectAction']),
    askToDelete(project) {
      this.projectToDelete = project;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteProject() {
      this.closeModal();
      if (this.projectToDelete) {
        await this.deleteProjectAction(this.projectToDelete);
      }
      await this.loadProjects();
    },
    async loadProjects() {
      this.message = 'getting the projects, please be patient';
      await this.getProjectsAction();
      this.message = '';
    },
  },
  computed: {
    ...mapState(['projects']),
    modalMessage() {
      const name =
        this.projectToDelete && this.projectToDelete.project_name
          ? this.projectToDelete.project_name
          : '';
      return `Would you like to delete ${name} ?`;
    },
  },
};
</script>
