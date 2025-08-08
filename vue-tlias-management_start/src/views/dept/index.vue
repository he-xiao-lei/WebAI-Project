<script setup>

// 响应式数据
import { ref,onMounted} from 'vue';
import { queryDeptApi,addDeptApi,queryDeptByIdApi,editDeptApi,deleteDeptByIdApi
  
 } from '@/api/dept';
import { ElMessage,ElMessageBox } from 'element-plus'
import { Edit } from '@element-plus/icons-vue';

// 发送请求获取部门列表
const search = async () => {
  const result = await queryDeptApi()
  deptList.value = result.data
}
onMounted(() => {
  search();
})
const deptList = ref([])
// 对话框
const dialogFormVisible = ref(false)

const dept = ref({
  name :  ''
})
// 新增
const addDept = () => {
  dialogFormVisible.value = true
  formTitle.value = '新增部门'
  dept.value = { name: '' }
  if (deptFormRef.value) {
    deptFormRef.value.resetFields();
  }

  
}
const formTitle = ref('')
// 保存
const save = async () => {
  // 表单校验
  if (!deptFormRef.value) return;
  deptFormRef.value.validate(async (valid) => { //表示是否校验通过,true则通过，false不通过
    
      let result;
    
    if (valid) {
      
      if (dept.value.id) {
        result = await editDeptApi(dept.value)
      } else {
        result = await addDeptApi(dept.value)
      }
        if (result.code) {//成功
        // 提示信息
          ElMessage.success("添加成功")
        //关闭对话框
          dialogFormVisible.value = false
        //查询数据
          search()
      } else {//失败
        // 操作失败会有失败信息
        ElMessage.error(result.msg)
      }
    } else {
      ElMessage.error("表单校验不通过")
    }
  })
}

const rules = ref({
  name: [
    { required: true, message: '部门名称是必填项', trigger: 'blur' },
    { min: 2, max: 10, message: 'Length should be 2 to 10', trigger: 'blur' },
  ],
})
const deptFormRef = ref();
// 查询回显
const edit = async (id) => {
  formTitle.value = '修改部门'
  const result = await queryDeptByIdApi(id);
  if (result.code) {
    dialogFormVisible.value = true
    dept.value = result.data
  }
  if (deptFormRef.value) {
    deptFormRef.value.resetFields();
  }
}
const delById = async (id) => {
  ElMessageBox.confirm(
    '确认删除该部门吗',
    'Warning',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
      const result = await deleteDeptByIdApi(id);
      if (result.code) {
        ElMessage.success("删除成功")
        search();
      } else {
        ElMessage.error(result.message)
      }
    })
    .catch(() => {
      ElMessage.success("您已取消删除")
    })
}
</script>

<template>
  <h1>部门管理</h1>
  <div class="button">
    <el-button type="primary" @click="addDept">添加部门</el-button>
  </div>
  <div>
   
    <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
      {{ dept }}
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <el-form-item label="部门名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="dept.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确定</el-button>
      </div>
    </template>
  </el-dialog>
    <!-- 表格 -->
    <el-table el-table :data="deptList" border style="width: 100%">
        <el-table-column type="index" width="60" label="序号" align="center" />
        <el-table-column prop="name" label="部门名称" width="180" align="center" />
        <el-table-column prop="updateTime" label="最后操作时间" align="center" />
        <el-table-column prop="operation" label="操作" align="center" >
      <template #default="scope">
        <el-button type="primary" size="large" @click="edit(scope.row.id)"><el-icon><edit /></el-icon>编辑</el-button>
        <el-button type="danger" size="large" @click="delById(scope.row.id)" ><el-icon><delete /></el-icon>删除</el-button>
      </template>
     </el-table-column>
    </el-table>
  </div>
  <div>
    
  </div>
</template>

<style scoped>
.button{
  margin: 10px 0;
}
</style>
