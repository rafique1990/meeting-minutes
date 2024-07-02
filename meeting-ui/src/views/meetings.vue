<template>
  <div class="content-container">
    <div class="columns">
      <div class="column is-8">
        <div class="section content-title-group">
          <h2 class="title">Meetings</h2>
          <button class="button refresh-button" @click="loadMeetings()">
            <i class="fas fa-sync"></i>
          </button>
          <router-link
            tag="button"
            class="button add-button"
            :to="{ name: 'meeting-detail', params: { id: 0 } }"
          >
            <i class="fas fa-plus"></i>
          </router-link>
          <ul>
            <li v-for="meeting in meetings" :key="meeting.meeting_id">
              <div class="card">
                <div class="card-content">
                  <div class="content">
                    <div :key="meeting.meeting_id" class="name">
                      {{ meeting.meeting_id }}
                    </div>
                    <div class="description">
                      {{ meeting.meeting_platform }}
                    </div>
                  </div>
                </div>
                <footer class="card-footer">
                  <button
                    class="link card-footer-item"
                    @click="askToDelete(meeting)"
                  >
                    <i class="fas fa-trash"></i>
                    <span>Delete</span>
                  </button>
                  <router-link
                    tag="button"
                    class="link card-footer-item"
                    :to="{
                      name: 'meeting-detail',
                      params: { id: meeting.meeting_id },
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
      @handleYes="deleteMeeting"
    >
    </Modal>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Modal from '@/components/modal';

export default {
  name: 'Meetings',
  data() {
    return {
      meetingToDelete: null,
      message: '',
      showModal: false,
    };
  },
  components: {
    Modal,
  },
  async created() {
    await this.loadMeetings();
  },
  methods: {
    ...mapActions(['getMeetingsAction', 'deleteMeetingAction']),
    askToDelete(meeting) {
      this.meetingToDelete = meeting;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteMeeting() {
      this.closeModal();
      if (this.meetingToDelete) {
        await this.deleteMeetingAction(this.meetingToDelete);
      }
      await this.loadMeetings();
    },
    async loadMeetings() {
      this.message = 'getting the meetings, please be patient';
      await this.getMeetingsAction();
      this.message = '';
    },
  },
  computed: {
    ...mapState(['meetings']),
    modalMessage() {
      const name =
        this.meetingToDelete && this.meetingToDelete.meeting_venue
          ? this.meetingToDelete.meeting_venue
          : '';
      return `Would you like to delete ${name} ?`;
    },
  },
};
</script>
