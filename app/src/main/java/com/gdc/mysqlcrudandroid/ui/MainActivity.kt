package com.gdc.mysqlcrudandroid.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdc.mysqlcrudandroid.R
import com.gdc.mysqlcrudandroid.adapter.UserAdapter
import com.gdc.mysqlcrudandroid.model.PostResponse
import com.gdc.mysqlcrudandroid.model.User
import com.gdc.mysqlcrudandroid.model.UserResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_update.view.*

class MainActivity : AppCompatActivity(), MainContract.View, UserAdapter.ClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var presenter: MainPresenter
    private var userList: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userAdapter = UserAdapter(this)
        presenter = MainPresenter(this)

    }

    fun clearField() {
        et_name.setText("")
        et_age.setText("")
        et_address.setText("")
    }

    override fun initActivity() {
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.setHasFixedSize(true)
        userAdapter.setClickListener(this)
        rv_user.adapter = userAdapter
    }

    override fun initListener() {
        btn_save.setOnClickListener {
            presenter.insertUser(et_name.text.toString().trim(),
                    et_age.text.toString().trim(),
                    et_address.text.toString().trim())
        }
        btn_show.setOnClickListener {
            presenter.showUser()
        }
    }

    override fun onShowResult(response: UserResponse) {
        userList.clear()
        userList.addAll(response.result!!)
        userAdapter.setUserList(userList)
    }

    override fun onInsertResult(response: PostResponse) {
        clearField()
        presenter.showUser()
        showMessage(response.pesan!!)
    }

    override fun onUpdateResult(response: PostResponse) {
        presenter.showUser()
        showMessage(response.pesan!!)
    }

    override fun onDeleteResult(response: PostResponse) {
        presenter.showUser()
        showMessage(response.pesan!!)
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progressBar.visibility = View.VISIBLE
                rv_user.visibility = View.GONE
            }
            false -> {
                progressBar.visibility = View.GONE
                rv_user.visibility = View.VISIBLE
            }
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMoreClick(position: Int, button: ImageButton) {
        val popup = PopupMenu(this, button)
        popup.menuInflater.inflate(R.menu.popup_main_menu, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_edit -> {
                    dialogEdit(position)
                }
                R.id.menu_delete -> {
                    dialogDelete(position)
                }
            }
            true
        }
        popup.show()
    }

    private fun dialogDelete(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Delete " + userList[position].nama)
        builder.setPositiveButton("Yes") { dialog, _ ->
            presenter.deleteUser(userList[position].id!!)
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ -> {
            dialog.dismiss()
        }}

        builder.show()
    }

    private fun dialogEdit(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_update, null)
        builder.setView(view)

        val dialog = builder.create()

        val etId = view.et_id
        val etName = view.et_name
        val etAge = view.et_age
        val etAddress = view.et_address
        val btnUpdate = view.btn_dialog_update

        etId.setText(userList[position].id)
        etName.setText(userList[position].nama)
        etAge.setText(userList[position].usia)
        etAddress.setText(userList[position].domisili)

        btnUpdate.setOnClickListener {
            presenter.updateUser(etId.text.toString(),
                    etName.text.toString(),
                    etAge.text.toString(),
                    etAddress.text.toString())
            dialog.dismiss()
        }

        dialog.show()
    }

}



















