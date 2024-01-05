package com.example.newproject

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import java.lang.IllegalStateException

private const val CONTACT_ID_INDEX = 0
private const val CONTACT_KEY_INDEX = 1
class ContactsFragment: Fragment(),LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {

    private val FROM_COLUMNS: Array<String> = arrayOf(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
    private val TO_IDS: IntArray = intArrayOf(R.id.contactTitleTextView)
private val PROJECTION: Array<out String> = arrayOf(
    ContactsContract.Contacts._ID,
    ContactsContract.Contacts.LOOKUP_KEY,
    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

    private val SELECTION: String = "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"
    private val searchString = SELECTION
    private val selectionArgs = arrayOf("")

    lateinit var contactsListView: ListView
    var selectedContactId: Long = 0
    var contactKey:String? = null
    var contactUri:Uri? = null
    private var cursorAdapter: SimpleCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoaderManager.getInstance(this).initLoader(0,null, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsListView = view.findViewById(R.id.contactsListView)
        cursorAdapter = SimpleCursorAdapter(
            view.context,
            R.layout.item_contact,
            null,
            FROM_COLUMNS,
            TO_IDS,
            0,
        )
        contactsListView.adapter = cursorAdapter
        contactsListView.onItemClickListener = this
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        selectionArgs[0] = "%$searchString%"
        activity?.let {
            return CursorLoader(it,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                selectionArgs,
                null
            )
        }?: throw IllegalStateException()
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursorAdapter?.swapCursor(null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursorAdapter?.swapCursor(data)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        val cursor: Cursor? = (parent.adapter as? CursorAdapter)?.cursor?.apply {
            moveToPosition(position)
            selectedContactId = getLong(CONTACT_ID_INDEX)
            contactKey = getString(CONTACT_KEY_INDEX)
            contactUri = ContactsContract.Contacts.getLookupUri(selectedContactId,contactKey)
        }
    }


}