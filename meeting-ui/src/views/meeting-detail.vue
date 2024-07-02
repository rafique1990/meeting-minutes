<template>
  <div>
    <div class="section content-title-group">
      <h2 class="title">{{ title }}</h2>
      <div class="card">
        <header class="card-header">
          <p class="card-header-title">{{ meeting.meeting_platform }}</p>
        </header>
        <div class="card-content">
          <div class="content">
                  <div class="field">
              <label class="label" for="id">id</label>
              <label class="input" name="id" readonly>{{ meeting.meeting_id }}</label>
            </div>
                 <div class="field">
              <label class="label" for="meeting_platform">meeting_platform</label>
              <input class="input" name="meeting_platform" v-model="meeting.meeting_platform" />
            </div>
            <div class="field">
              <label class="label" for="meeting_link">meeting_link</label>
              <input class="input" name="meeting_link" v-model="meeting.meeting_link" />
            </div>
            <div class="field">
              <label class="label" for="meeting_venue">meeting_venue</label>
              <input class="input" name="meeting_venue" v-model="meeting.meeting_venue" />
            </div>
            <div class="field">
              <label class="label" for="meeting_agenda">meeting_agenda</label>
              <input class="input" name="meeting_agenda" v-model="meeting.meeting_agenda" />
            </div>
            <div class="field">
              <label class="label" for="meeting_minutes">meeting_minutes</label>
              <input class="input" name="meeting_minutes" v-model="meeting.meeting_minutes" />
            </div>
            <div class="field">
              <label class="label" for="project_id">project id</label>
              <input class="input" name="project_id" v-model="meeting.project_id" />
            </div>
            <div class="field">
              <label class="label" for="meetingdate">meeting date</label>
              <input class="input" name="meetingdate" v-model="meeting.meeting_date" />
            </div>
          </div>
        </div>
        <footer class="card-footer">
          <button
            class="link card-footer-item cancel-button"
            @click="cancelMeeting()"
          >
            <i class="fas fa-undo"></i>
            <span>Cancel</span>
          </button>
          <button class="link card-footer-item" @click="saveMeeting()">
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
  name: 'MeetingDetail',
  props: {
    id: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      meeting: {},
    };
  },
  created() {
    if (this.isAddMode) {
      this.meeting = {
        meeting_id: undefined,
        meeting_platform: '',
        meeting_link: '',
        meeting_venue: '',
        meeting_agenda: '',
        meeting_minutes: '',
        project_id: '',
        meeting_date: ''
      };
    } else {
      this.meeting = { ...this.getMeetingById(this.id) };
    }
  },
  computed: {
    ...mapGetters(['getMeetingById']),
    isAddMode() {
      return !this.id;
    },
    title() {
      return `${this.isAddMode ? 'Add' : 'Edit'} Meeting`;
    },
  },
  methods: {
    ...mapActions(['updateMeetingAction', 'addMeetingAction']),
    cancelMeeting() {
      this.$router.push({ name: 'meetings' });
    },
    async saveMeeting() {
      this.meeting.meeting_id
        ? await this.updateMeetingAction(this.meeting)
        : await this.addMeetingAction(this.meeting);
      this.$router.push({ name: 'meeting' });
    },
//     async saveMeeting() {
//       if(this.isAddMode){

// await this.addMeetingAction(this.meeting);
//       }
//       else {
// await this.updateMeetingAction(this.meeting)

//       }
  
//       this.$router.push({ name: 'meeting' });
//     },
  },
};
</script>
