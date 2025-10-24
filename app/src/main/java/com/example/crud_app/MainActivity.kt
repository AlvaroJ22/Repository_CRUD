package com.example.crud_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var btnAgregar: Button? = null
    private var materiasCRUD: MateriasCRUD? = null
    private var listaMaterias: ArrayList<Materia>? = null
    private var adapter: ArrayAdapter<String?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewMaterias)
        btnAgregar = findViewById(R.id.btnAgregar)

        materiasCRUD = MateriasCRUD(this)
        listaMaterias = materiasCRUD!!.obtainSubjects()

        actualizarAdapter()

        // Botón agregar
        btnAgregar!!.setOnClickListener {
            val vistaDialogo: View = layoutInflater.inflate(R.layout.dialogo_agregar, null)
            val etMateria = vistaDialogo.findViewById<EditText>(R.id.etMateria)
            val etCreditos = vistaDialogo.findViewById<EditText>(R.id.etCreditos)

            AlertDialog.Builder(this@MainActivity)
                .setTitle("Agregar Materia")
                .setView(vistaDialogo)
                .setPositiveButton("Agregar") { dialog, which ->
                    val nombre = etMateria.text.toString()
                    val creditos = etCreditos.text.toString().toInt()

                    val nueva = Materia(nombre, creditos)
                    materiasCRUD!!.insertSubject(nueva)
                    refrescarLista()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        // Click simple: editar materia
        listView!!.setOnItemClickListener { _, _, position, _ ->
            val materiaSeleccionada = listaMaterias!![position]
            val vistaDialogo: View = layoutInflater.inflate(R.layout.dialogo_agregar, null)

            val etMateria = vistaDialogo.findViewById<EditText>(R.id.etMateria)
            val etCreditos = vistaDialogo.findViewById<EditText>(R.id.etCreditos)

            // Cargar datos existentes
            etMateria.setText(materiaSeleccionada.getMateria())
            etCreditos.setText(materiaSeleccionada.getCreditos().toString())

            AlertDialog.Builder(this@MainActivity)
                .setTitle("Editar Materia")
                .setView(vistaDialogo)
                .setPositiveButton("Guardar") { _, _ ->
                    val nombre = etMateria.text.toString()
                    val creditos = etCreditos.text.toString().toInt()

                    materiaSeleccionada.setMateria(nombre)
                    materiaSeleccionada.setCreditos(creditos)
                    materiasCRUD!!.updateSubject(materiaSeleccionada)
                    refrescarLista()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        // Click largo: eliminar materia
        listView!!.setOnItemLongClickListener { _, _, position, _ ->
            val materiaSeleccionada = listaMaterias!![position]

            AlertDialog.Builder(this@MainActivity)
                .setTitle("Eliminar Materia")
                .setMessage("¿Deseas eliminar ${materiaSeleccionada.getMateria()}?")
                .setPositiveButton("Sí") { _, _ ->
                    materiasCRUD!!.deleteSubject(materiaSeleccionada)
                    refrescarLista()
                }
                .setNegativeButton("Cancelar", null)
                .show()

            true
        }
    }

    private fun refrescarLista() {
        listaMaterias = materiasCRUD!!.obtainSubjects()
        actualizarAdapter()
    }

    private fun actualizarAdapter() {
        val nombres = ArrayList<String?>()
        for (m in listaMaterias!!) {
            nombres.add("${m.getMateria()} (${m.getCreditos()} créditos)")
        }
        if (adapter == null) {
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)
            listView!!.adapter = adapter
        } else {
            adapter!!.clear()
            adapter!!.addAll(nombres)
            adapter!!.notifyDataSetChanged()
        }
    }
}
