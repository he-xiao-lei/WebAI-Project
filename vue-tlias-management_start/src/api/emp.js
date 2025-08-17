import request from "@/utils/request";


// 查询全部员工数据
export const queryEmpApi = (name,gender,begin,end,page,pageSize) => request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)
// 根据id查询
export const queryEmpByIdApi = (id) => request.get(`/emps/${id}`)

// 新增
// 
// export const queryDeptByIdApi = (id) => request.get(`/depts/${id}`)

// 新增
export const addEmpApi = (emp) => request.post('/emps', emp)
// 删除
export const deleteEmpByIdApi = (ids) => request.delete(`/emps?ids=${ids}`)

//修改
export const editEmpApi = (emp) => request.put('/emps', emp)

