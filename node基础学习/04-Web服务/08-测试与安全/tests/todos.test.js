/**
 * API 测试 — 使用 Node 内置 test + supertest
 * 运行：npm test
 */
const { describe, it } = require('node:test');
const assert = require('node:assert/strict');
const request = require('supertest');
const app = require('../src/app');

describe('Todo API', () => {
  it('GET / 返回服务信息', async () => {
    const res = await request(app).get('/');
    assert.equal(res.status, 200);
    assert.equal(res.body.name, '测试与安全 Demo');
  });

  it('GET /api/todos 返回列表', async () => {
    const res = await request(app).get('/api/todos');
    assert.equal(res.status, 200);
    assert.equal(res.body.code, 0);
    assert.ok(Array.isArray(res.body.data));
  });

  it('POST /api/todos 创建成功', async () => {
    const res = await request(app)
      .post('/api/todos')
      .send({ title: 'supertest 测试项' })
      .set('Content-Type', 'application/json');

    assert.equal(res.status, 201);
    assert.equal(res.body.code, 0);
    assert.equal(res.body.data.title, 'supertest 测试项');
  });

  it('POST /api/todos title 为空返回 400', async () => {
    const res = await request(app)
      .post('/api/todos')
      .send({ title: '' })
      .set('Content-Type', 'application/json');

    assert.equal(res.status, 400);
  });

  it('DELETE /api/todos/:id 删除不存在返回 404', async () => {
    const res = await request(app).delete('/api/todos/99999');
    assert.equal(res.status, 404);
  });
});

describe('安全响应头', () => {
  it('helmet 应设置 X-Content-Type-Options', async () => {
    const res = await request(app).get('/');
    assert.equal(res.headers['x-content-type-options'], 'nosniff');
  });
});
