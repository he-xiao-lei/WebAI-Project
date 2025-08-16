import request from "@/utils/request";


// 查询全部员工数据
export const queryEmpApi = (name,gender,begin,end,page,pageSize) => request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)

// 
// export const queryDeptByIdApi = (id) => request.get(`/depts/${id}`)

// 新增
export const addEmpApi = (emp) => request.post('/emps', emp)
// 删除
// export const deleteDeptByIdApi = (id) => request.delete(`/depts?id=${id}`)

//修改
// export const editDeptApi = (dept) => request.put('/depts', dept)

