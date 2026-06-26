/**
 * 控制器 — 连接 HTTP 与 service
 */
const todoService = require('../services/todoService');
const { ok, fail } = require('../utils/response');

exports.list = (req, res) => {
  const data = todoService.list(req.query);
  ok(res, data);
};

exports.getById = (req, res) => {
  const data = todoService.getById(Number(req.params.id));
  ok(res, data);
};

exports.create = (req, res) => {
  try {
    const data = todoService.create(req.body.title);
    ok(res, data, 201);
  } catch (err) {
    fail(res, err.status || 400, err.message);
  }
};

exports.update = (req, res) => {
  const data = todoService.update(Number(req.params.id), req.body);
  ok(res, data);
};

exports.remove = (req, res) => {
  const data = todoService.remove(Number(req.params.id));
  ok(res, data);
};
