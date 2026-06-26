/**
 * 业务层 — 只管数据和逻辑，不出现 req/res
 */
let todos = [
  { id: 1, title: '拆分 routes', done: true },
  { id: 2, title: '拆分 controllers', done: false },
];
let nextId = 3;

function list(query = {}) {
  let result = [...todos];
  if (query.done !== undefined) {
    const flag = query.done === 'true' || query.done === true;
    result = result.filter((t) => t.done === flag);
  }
  return result;
}

function getById(id) {
  const todo = todos.find((t) => t.id === id);
  if (!todo) {
    const err = new Error('Todo 不存在');
    err.status = 404;
    throw err;
  }
  return todo;
}

function create(title) {
  if (!title?.trim()) {
    const err = new Error('title 不能为空');
    err.status = 400;
    throw err;
  }
  const todo = { id: nextId++, title: title.trim(), done: false };
  todos.push(todo);
  return todo;
}

function update(id, patch) {
  const todo = getById(id);
  if (patch.title !== undefined) todo.title = String(patch.title).trim();
  if (typeof patch.done === 'boolean') todo.done = patch.done;
  return todo;
}

function remove(id) {
  const index = todos.findIndex((t) => t.id === id);
  if (index === -1) {
    const err = new Error('Todo 不存在');
    err.status = 404;
    throw err;
  }
  return todos.splice(index, 1)[0];
}

module.exports = { list, getById, create, update, remove };
