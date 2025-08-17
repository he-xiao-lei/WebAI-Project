<script setup>
import { onMounted } from "vue";
import { ElMessageBox } from "element-plus";
import { queryEmpApi, queryEmpByIdApi, editEmpApi, deleteEmpByIdApi } from "@/api/emp";
import { ref, watch } from "vue";
import { queryDeptApi } from "@/api/dept";
import { ElMessage } from "element-plus";
import { addEmpApi } from "@/api/emp";

//元数据
//职位列表数据
const jobs = ref([
  { name: "班主任", value: 1 },
  { name: "讲师", value: 2 },
  { name: "学工主管", value: 3 },
  { name: "教研主管", value: 4 },
  { name: "咨询师", value: 5 },
  { name: "其他", value: 6 },
]);
//性别列表数据
const genders = ref([
  { name: "男", value: 1 },
  { name: "女", value: 2 },
]);
//新增/修改表单
const employee = ref({
  username: "",
  name: "",
  gender: "",
  phone: "",
  job: "",
  salary: "",
  deptId: "",
  entryDate: "",
  image: "",
  exprList: [],
});
//表单校验规则
const rules = ref({
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 2, max: 20, message: "用户名长度应在2到20个字符之间", trigger: "blur" },
  ],
  name: [
    { required: true, message: "请输入姓名", trigger: "blur" },
    { min: 2, max: 10, message: "姓名长度应在2到10个字符之间", trigger: "blur" },
  ],
  gender: [{ required: true, message: "请选择性别", trigger: "change" }],
  phone: [
    { required: true, message: "请输入手机号", trigger: "blur" },
    { pattern: /^1\d{10}$/, message: "请输入有效的手机号", trigger: "blur" },
  ],
});
// 声明一个表单的引用

const FormRef = ref();
const depts = ref([]);
// 控制弹窗
const dialogVisible = ref(false);
const dialogTitle = ref("新增员工");
// 新增员工函数
const addEmp = () => {
  dialogVisible.value = true;
  dialogTitle.value = "新增员工";
  employee.value = {
    username: "",
    name: "",
    gender: "",
    phone: "",
    job: "",
    salary: "",
    deptId: "",
    entryDate: "",
    image: "",
    exprList: [],
  };
  // 重置表单提示信息
  if (FormRef.value) {
    FormRef.value.resetFields();
  }
};
const searchEmp = ref({
  name: "",
  gender: "",
  date: [],
  begin: "",
  end: "",
});
// 监听
watch(
  () => searchEmp.value.date,
  (newV, oldV) => {
    searchEmp.value.begin = newV[0];
    console.log(`begin ${newV[0]}`);
    searchEmp.value.end = newV[1];
    console.log(`end ${newV[1]}`);
  },
  { deep: true }
);
// 查询
const search = async () => {
  const result = await queryEmpApi(
    searchEmp.value.name,
    searchEmp.value.gender,
    searchEmp.value.begin,
    searchEmp.value.end,
    currentPage.value,
    pageSize.value
  );
  if (result.code) {
    empList.value = result.data.rows;
    total.value = result.data.total;
  }
};
// 查询回显
const edit = async (id) => {
  const result = await queryEmpByIdApi(id);

  if (result.code) {
    dialogTitle.value = "修改员工";
    dialogVisible.value = true;
    employee.value = result.data;
    // 因为前端有exprDate这个值但是后端传过来没有，所以要重新赋值
    let exprList = employee.value.exprList;
    if (exprList.length > 0 && exprList) {
      exprList.forEach((item) => {
        item.exprDate = [item.begin, item.end];
      });
    }
  }
  // 校验规则清除区域
};
const token = ref();
const getToken = () => {
  token.value = localStorage.getItem("token");
};

// 清空
const clear = () => {
  searchEmp.value = { name: "", gender: "", date: [], begin: "", end: "" };
  search();
};
// 删除员工
const deleteEmps = async (ids) => {
  ElMessageBox.confirm("确认删除该员工吗", "Warning", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(async () => {
      const result = await deleteEmpByIdApi(ids);
      if (result.code) {
        ElMessage.success("删除成功");
        search();
      } else {
        ElMessage.error(result.message);
      }
    })
    .catch(() => {
      ElMessage.success("您已取消删除");
    });
};
// 批量删除的员工id存储数组
const empIds = ref([]);
const handleSelectionChange = (ids) => {
  empIds.value = ids.map((item) => {
    return item.id;
  });
};
// 批量删除方法
const deleteEmpsByIds = async () => {
  ElMessageBox.confirm("确认删除选中的员工吗", "Warning", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(async () => {
      if (empIds.value && empIds.value.length > 0) {
        const result = await deleteEmpByIdApi(empIds.value);
        if (result.code) {
          ElMessage.success("删除成功");
          search();
        } else {
          ElMessage.error(result.message);
        }
      } else {
        ElMessage.error("你没有选择任何员工");
      }
    })
    .catch(() => {
      ElMessage.success("您已取消删除");
    });
};

onMounted(() => {
  search();
  queryDepts();
  getToken();
});
const queryDepts = async () => {
  const result = await queryDeptApi();
  if (result.code) {
    depts.value = result.data;
  }
};
//watch侦听===================================
//侦听一个响应式数据
// watch(a, (newV, oldV) => {
//   console.log(`变化后的值${newV},变化前的值${oldV}`);
// });
// 侦听一个对象所有属性
// const user = ref({ name: "", age: 10 });
// watch(
//   user,
//   (newV, oldV) => {
//     console.log(newV);
//   },
//   { deep: true }
// );
// // 侦听单个属性
// watch(
//   () => user.value.age,
//   (newV, oldV) => {
//     console.log(newV);
//   },
//   { deep: true }
// );

const empList = ref([
  {
    //   id: 88,
    //   username: "Test",
    //   password: "123456",
    //   name: "Test",
    //   gender: 1,
    //   phone: "19908200721",
    //   job: 53,
    //   salary: 16,
    //   image:
    //     "https://cn.bing.com/images/search?q=%e5%9b%be%e7%89%87%e4%ba%8c%e6%ac%a1%e5%85%83&id=24D6005E99310E2C86487C937DD3191AE7608883&FORM=IACFIR",
    //   entryDate: "2025-04-25",
    //   deptId: 2,
    //   createTime: "2025-08-14T09:47:54",
    //   updateTime: "2025-08-14T09:47:54",
    //   deptName: "教研部",
    //   exprList: [
    //     {
    //       id: 44,
    //       empId: 88,
    //       begin: null,
    //       end: "2022-10-06",
    //       company: "in enim laboris",
    //       job: "Lorem ci",
    //     },
    //     {
    //       id: 43,
    //       empId: 88,
    //       begin: null,
    //       end: "1955-10-07",
    //       company: "cupidatat",
    //       job: "est in",
    //     },
    //   ],
    // },
  },
]);
// 分页部分
const currentPage = ref(1); //当前页码
const pageSize = ref(10); // 每页显示数量
const background = ref(true); // 是否有背景色
const total = ref(0);
// 每页展示记录数变化时触发
const handleSizeChange = (val) => {
  search();
};
// 页码变化时触发
const handleCurrentChange = (val) => {
  // 修改时因为双向绑定的原因，修改了currentPage后，会自动绑定到currentPage上,所以直接search就好了
  search();
};
// 上传图片
//文件上传
// 图片上传成功后触发
const handleAvatarSuccess = (response) => {
  employee.value.image = response.data;
};

// 文件上传之前触发
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
    ElMessage.error("只支持上传图片");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error("只能上传10M以内图片");
    return false;
  }
  return true;
};
// 添加工作经历

const addExpr = () => {
  employee.value.exprList.push({
    company: "",
    job: "",
    begin: "",
    end: "",
    exprDate: [],
  });
  begin = exprDate[0];
  end = exprDate[1];
};
// 使用 watch 侦听 exprList 的变化
watch(
  () => employee.value.exprList,
  (newValue, oldValue) => {
    if (employee.value.exprList && employee.value.exprList > 0) {
      employee.value.exprList.forEach((expr) => {
        expr.begin = expr.exprDate[0];
        expr.end = expr.exprDate[1];
      });
    }
  }
);
// 删除工作经历
const delEmpExpr = () => {
  employee.value.exprList.pop({
    company: "",
    job: "",
    begin: "",
    end: "",
    exprDate: [],
  });
};
const save = async () => {
  // 表单校验
  if (!FormRef.value) return;
  FormRef.value.validate(async (valid) => {
    //表示是否校验通过,true则通过，false不通过

    let result;

    if (valid) {
      if (employee.value.id) {
        //判断id是否存在，后端返回的
        result = await editEmpApi(employee.value);
      } else {
        result = await addEmpApi(employee.value);
      }
      if (result.code) {
        //成功
        // 提示信息
        ElMessage.success("操作成功");
        //关闭对话框
        dialogVisible.value = false;
        //查询数据
        search();
      } else {
        //失败
        // 操作失败会有失败信息
        ElMessage.error(result.msg);
      }
    } else {
      ElMessage.error("表单校验不通过");
    }
  });
};
// 复选框发生变化时触发
</script>

<template>
  <!-- 新增员工对话框(修改和新增都要用) -->
  <!-- form表单和dialog -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="employee" label-width="80px" :rules="rules" ref="FormRef">
      <!-- 基本信息 -->
      <!-- 第一行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input
              v-model="employee.username"
              placeholder="请输入员工用户名，2-20个字"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input
              v-model="employee.name"
              placeholder="请输入员工姓名，2-10个字"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select
              v-model="employee.gender"
              placeholder="请选择性别"
              style="width: 100%"
            >
              <el-option
                v-for="g in genders"
                :key="g.value"
                :label="g.name"
                :value="g.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="手机号" prop="gender">
            <el-input v-model="employee.phone" placeholder="请输入员工手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="职位">
            <el-select
              v-model="employee.job"
              placeholder="请选择职位"
              style="width: 100%"
            >
              <el-option
                v-for="job in jobs"
                :key="job.value"
                :label="job.name"
                :value="job.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="薪资">
            <el-input v-model="employee.salary" placeholder="请输入员工薪资"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第四行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属部门">
            <el-select
              v-model="employee.deptId"
              placeholder="请选择部门"
              style="width: 100%"
            >
              <el-option
                v-for="d in depts"
                :key="d.id"
                :label="d.name"
                :value="d.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="入职日期">
            <el-date-picker
              v-model="employee.entryDate"
              type="date"
              style="width: 100%"
              placeholder="选择日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="头像">
            <el-upload
              class="avatar-uploader"
              action="/api/upload"
              :headers="{ token: token }"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="employee.image" :src="employee.image" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 工作经历 -->
      <!-- 第六行 -->
      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="工作经历">
            <el-button type="success" size="small" @click="addExpr"
              >+ 添加工作经历</el-button
            >
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第七行 ...  工作经历 -->
      <el-row :gutter="3" v-for="expr in employee.exprList">
        <el-col :span="10">
          <el-form-item size="small" label="时间" label-width="80px">
            <el-date-picker
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              v-model="expr.exprDate"
            ></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="公司" label-width="60px">
            <el-input placeholder="请输入公司名称" v-model="expr.company"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="职位" label-width="60px">
            <el-input placeholder="请输入职位" v-model="expr.job"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="2">
          <el-form-item size="small" label-width="0px">
            <el-button type="danger" @click="delEmpExpr">- 删除</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </template>
  </el-dialog>
  <h1>员工管理</h1>
  <!-- 搜索框 -->
  <div class="container">
    <el-form :inline="true" :model="searchEmp" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="searchEmp.name" placeholder="请输入员工姓名" clearable />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="searchEmp.gender" placeholder="请选择" clearable>
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间">
        <div class="demo-date-picker">
          <el-date-picker
            v-model="searchEmp.date"
            type="daterange"
            range-separator="到"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :size="size"
          />
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 功能按钮 -->
  <div class="container">
    <el-button type="primary" @click="addEmp">+ 新增员工</el-button>
    <el-button type="danger" @click="deleteEmpsByIds">- 批量删除</el-button>
  </div>
  <!-- 表格 -->
  <div class="container">
    <el-table
      :data="empList"
      border
      style="width: auto"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="name" label="名字" width="120" align="center" />
      <el-table-column label="性别" width="120" align="center">
        <template #default="scope">
          {{ scope.row.gender == 1 ? "男" : "女" }}
        </template>
      </el-table-column>
      <el-table-column label="头像 " width="180" align="center">
        <template #default="scope">
          <img :src="scope.row.image" height="40px" />
        </template>
      </el-table-column>
      <el-table-column prop="deptName" label="所属部门" width="180" align="center" />
      <el-table-column label="职位" width="180" align="center">
        <template #default="scope">
          <span v-if="scope.row.job == 1">班主任</span>
          <span v-else-if="scope.row.job == 2">讲师</span>
          <span v-else-if="scope.row.job == 3">学工主管</span>
          <span v-else-if="scope.row.job == 4">校验主管</span>
          <span v-else-if="scope.row.job == 5">咨询师</span>
          <span v-else>其他</span>
        </template>
      </el-table-column>

      <el-table-column prop="entryDate" label="入职时间" width="180" align="center" />
      <el-table-column
        prop="updateTime"
        label="最后操作日期"
        width="180"
        align="center"
      />
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)"
            ><el-icon><edit /></el-icon>编辑</el-button
          >
          <el-button type="danger" size="small" @click="deleteEmps(scope.row.id)"
            ><el-icon><delete /></el-icon>删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页 -->

  <div class="demo-pagination-block">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>
<style scoped>
.container {
  margin-top: 10px;
}
.avatar {
  height: 40px;
}
.avatar-uploader .avatar {
  width: 78px;
  height: 78px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  text-align: center;
  border-radius: 10px;
  /* 添加灰色的虚线边框 */
  border: 1px dashed var(--el-border-color);
}
</style>
