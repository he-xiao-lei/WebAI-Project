import request from "@/utils/request";
// 查询全部部门数据
export const queryDeptApi = () => request.get('/depts')

// 根据id查询
export const queryDeptByIdApi = (id) => request.get(`/depts/${id}`)

// 新增
// export const addDeptApi
export const addDeptApi = (dept) => request.post('/depts', dept)
// 删除
export const deleteDeptByIdApi = (id) => request.delete(`/depts?id=${id}`)

//修改
export const editDeptApi = (dept) => request.put('/depts', dept)

