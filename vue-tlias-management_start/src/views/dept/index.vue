<script setup>

// 响应式数据
import { ref,onMounted} from 'vue';
import { queryDeptApi } from '@/api/dept';

// 发送请求获取部门列表
const search = async () => {
  const result = await queryDeptApi()
  deptList.value = result.data
}
onMounted(() => {
  search();
})
const deptList = ref([])
</script>

<template>
  <h1>部门管理</h1>
  <div class="button">
    <el-button type="primary">添加部门</el-button>
  </div>
  <div>
    <!-- 表格 -->
    <el-table el-table :data="deptList" border style="width: 100%">
        <el-table-column type="index" width="60" label="序号" align="center" />
        <el-table-column prop="name" label="部门名称" width="180" align="center" />
        <el-table-column prop="updateTime" label="最后操作时间" align="center" />
        <el-table-column prop="operation" label="操作" align="center" >
      <template #default="scope">
        <el-button type="primary" size="small"><el-icon><edit /></el-icon>编辑</el-button>
        <el-button type="danger" size="small"><el-icon><delete /></el-icon>删除</el-button>
      </template>
     </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>
.button{
  margin: 10px 0;
}
</style>
