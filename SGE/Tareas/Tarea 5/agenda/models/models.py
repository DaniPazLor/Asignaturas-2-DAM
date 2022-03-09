# -*- coding: utf-8 -*-

from odoo import models, fields, api

class agenda(models.Model):
     _name = 'agenda.agenda'
     _description = 'Permite almacenar números de teléfonos'

     Nombre = fields.Char('Nombre')
     Telefono = fields.Integer('Teléfono')






































     # from odoo import models, fields, api

     # class agenda(models.Model):
     #     _name = 'agenda.agenda'
     #     _description = 'agenda.agenda'

     #     name = fields.Char()
     #     value = fields.Integer()
     #     value2 = fields.Float(compute="_value_pc", store=True)
     #     description = fields.Text()
     #
     #     @api.depends('value')
     #     def _value_pc(self):
     #         for record in self:
     #             record.value2 = float(record.value) / 100