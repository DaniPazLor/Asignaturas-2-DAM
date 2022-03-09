# -*- coding: utf-8 -*-
{
    'name': 'Agenda',
    'summary': 'Agenda telefónica',
    'description': """Módulo que sirve para la gestión de la agenda telefónica""",

    'author': "Daniel Paz Lorenzo",
    'website': "http://www.Tarea5SGE.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/14.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Services',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        # 'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'views/agenda_view.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
}
