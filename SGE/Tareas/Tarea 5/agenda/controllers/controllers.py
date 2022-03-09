# -*- coding: utf-8 -*-
# from odoo import http


# class Agenda(http.Controller):
#     @http.route('/agenda/agenda/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/agenda/agenda/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('agenda.listing', {
#             'root': '/agenda/agenda',
#             'objects': http.request.env['agenda.agenda'].search([]),
#         })

#     @http.route('/agenda/agenda/objects/<model("agenda.agenda"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('agenda.object', {
#             'object': obj
#         })
