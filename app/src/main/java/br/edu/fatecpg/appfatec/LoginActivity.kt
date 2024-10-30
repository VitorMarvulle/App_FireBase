package br.edu.fatecpg.appfatec

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.appfatec.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnSair.setOnClickListener {
            // Encerra a sessão do usuário
            auth.signOut()
            // Redireciona para a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a LoginActivity
        }

        binding.btnExcluir.setOnClickListener {
            // Excluir o usuário do Firebase Authentication
            val user = auth.currentUser
            user?.delete()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário excluído com sucesso!", Toast.LENGTH_SHORT).show()
                    // Redireciona para a MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finaliza a LoginActivity
                } else {
                    Toast.makeText(this, "Erro ao excluir usuário: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}