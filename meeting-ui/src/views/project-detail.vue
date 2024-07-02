<template>
  <div>
    <div class="section content-title-group">
      <h2 class="title">{{ title }}</h2>
      <div class="card">
        <header class="card-header">
          <p class="card-header-title">{{ project.project_name }}</p>
        </header>
        <div class="card-content">
          <div class="content">
               <div class="field">
              <label class="label" for="id">id</label>
              <label class="input" name="id" readonly>{{ project.project_id }}</label>
            </div>
            <div class="field">
              <label class="label" for="projectName">project_name</label>
                <input
                  class="input"
                  name="projectName"
                  v-model="project.project_name"/>
            </div>
            <div class="field">
              <label class="label" for="active">active</label>
                <input class="input" name="active" v-model="project.active" />
            </div>
            <div class="field">
              <label class="label" for="startDate">start date</label>
                <input
                  class="input"
                  name="start_date"
                  v-model="project.start_date"
                />
            </div>
            <div class="field">
              <label class="label" for="endDate">end date</label>
                <input
                  class="input"
                  name="endDate"
                  v-model="project.end_date"
                />
            </div>
          </div>
        </div>
        <footer class="card-footer">
          <button
            class="link card-footer-item cancel-button"
            @click="cancelProject()"
          >
            <i class="fas fa-undo"></i>
            <span>Cancel</span>
          </button>
          <button class="link card-footer-item" @click="saveProject()">
            <i class="fas fa-save"></i>
            <span>Save</span>
          </button>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'ProjectDetail',
  props: {
    id: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      project: {},
    };
  },
  created() {
    if (this.isAddMode) {
      this.project = {
        project_id: undefined,
        project_name: '',
        active: false,
        start_date: '',
        end_date: '',
      };
    } else {
      this.project = { ...this.getProjectById(this.id) };
    }
  },
  computed: {
    ...mapGetters(['getProjectById']),
    isAddMode() {
      return !this.id;
    },
    title() {
      return `${this.isAddMode ? 'Add' : 'Edit'} Project`;
    },
  },
  methods: {
    ...mapActions(['updateProjectAction', 'addProjectAction']),
    cancelProject() {
      this.$router.push({ name: 'projects' });
    },
    async saveProject() {
      this.project.project_id
        ? await this.updateProjectAction(this.project)
        : await this.addProjectAction(this.project);
      this.$router.push({ name: 'projects' });
    },
    //     async saveProject() {
    //   if(this.isAddMode){
    // await this.addProjectAction(this.project);
    //   }
    //   else {
    //  await this.updateProjectAction(this.project)
    //   }
         
    //   this.$router.push({ name: 'projects' });
    // },
  },
};
</script>
