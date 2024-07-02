# frontend-meeting

###install node js
Install the node js for the respective OS from https://nodejs.org/en/download/ .

Vue CLI 4.x requires Node.js version 8.9 or above (v10+ recommended). 

###Check npm version
```
npm -v
```
### install vue js cli
```
npm install -g vue
```

##install vue js cli
```
npm install -g @vue/cli @vue/cli-service-global
```

##Check the Vue JS version
```
vue --version
```
## Project setup

```
cd frontend-meeting
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Trouble-shooting
if for some reason  npm run serve does not start the application,try following things on linux:
```
rm -r package-lock.json 
rm -r node_modules/
```

For some reasons in linux,npm requires file-write permissions,use sudo with npom command
```
sudo npm install
sudo npm run serve
```
### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
