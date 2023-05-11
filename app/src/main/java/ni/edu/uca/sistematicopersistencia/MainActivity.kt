package ni.edu.uca.sistematicopersistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos.Companion.obtBaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var producto: EntityProducto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        producto = intent.getSerializableExtra("basedatos") as EntityProducto?



        binding.btnGuardar.setOnClickListener { addProducto() }
    }

    private fun addProducto() {

        val nombre = binding.etNombres.text.toString()
        val precio = binding.etPrecio.text.toString().toDouble()
        val existencia = binding.etExistencia.text.toString().toInt()


        lifecycleScope.launch {
            if (producto == null) {
                val producto = EntityProducto(nombre = nombre, precio = precio, existencia = existencia)
                obtBaseDatos(this@MainActivity).productoDao().insertarReg(producto)
                finish()

        }
    }
}


    }
