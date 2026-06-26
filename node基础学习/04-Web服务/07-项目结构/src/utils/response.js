function ok(res, data, status = 200) {
  res.status(status).json({ code: 0, data, message: 'success' });
}

function fail(res, status, message) {
  res.status(status).json({ code: status, data: null, message });
}

module.exports = { ok, fail };
