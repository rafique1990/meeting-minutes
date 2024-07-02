module.exports = {
  configureWebpack: {
    devtool: 'source-map',
  },
  devServer: {
    proxy: {
      '/api/v1/': {
       target: 'http://meetingbackend:8080', // target host
//        target: 'http://localhost:8080', // target host
        ws: true, // proxy websockets
        changeOrigin: true, // needed for virtual hosted sites
      },
    },
  },
};
