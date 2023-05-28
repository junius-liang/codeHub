<template>
<div>
  <h1>Spring Security 登录页面</h1>
  <form>
    <table>
      <tr>
        <td>用户名</td>
        <td><input type="text" name="username" v-model="userVal"></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input type="text" name="pwd" v-model="passwordVal"></td>
      </tr>
    </table>
  </form>
  <button @click="submit">提交</button>

</div>
</template>

<script lang="ts" setup>
import { onMounted,ref } from 'vue'
import axios from "axios";
let userVal = ref('')
let passwordVal = ref('')
const getStudent = ()=>{
  axios({
    method: 'get',
    url: 'http://127.0.0.1:8080/getStudent',
  }).then(function (response) {
        console.log(response)
      });
}
const postLogin = ()=>{
  axios({
    method: 'get',
    url: 'http://127.0.0.1:8080/doLogin',
    data:{
      username:userVal.value,
      pwd:passwordVal.value
    }
  }).then(function (response) {
    console.log(response)
  });
}

const submit = ()=>{
  console.log(userVal.value)
  console.log(passwordVal.value)
  postLogin()
  userVal.value=''
  passwordVal.value=''
}
onMounted(() => {
  console.log(`the component is now mounted.`)
})
const say = ()=>{
  getStudent();
}
</script>

