const express = require('express');
const todoController = require('../controllers/todoController');
const asyncHandler = require('../middleware/asyncHandler');

const router = express.Router();

router.get('/', todoController.list);
router.get('/:id', asyncHandler(async (req, res) => todoController.getById(req, res)));
router.post('/', todoController.create);
router.put('/:id', asyncHandler(async (req, res) => todoController.update(req, res)));
router.delete('/:id', asyncHandler(async (req, res) => todoController.remove(req, res)));

module.exports = router;
