package com.challenge.bbva.challengebbva;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.challenge.bbva.challengebbva.api.ApiService;
import com.challenge.bbva.challengebbva.client.JsonClient;
import com.challenge.bbva.challengebbva.rest.Producto;

import com.challenge.bbva.challengebbva.rest.ProductoLista;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by clazoe
 */

public class MainActivity extends AppCompatActivity {
    SwipeMenuListView simpleList;
    private ProductoAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleList = (SwipeMenuListView)findViewById(R.id.simpleListView);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(170);
                openItem.setTitle("Open");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(170);
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        simpleList.setMenuCreator(creator);



        simpleList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // abrir
                        break;
                    case 1:
                        // borrar
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, ArrayList<Producto>> {

        private ArrayList<Producto> productoLista=null;
        DBHandler db = new DBHandler(MainActivity.this);

        public ArrayList<Producto> getProductoLista() {
            return productoLista;
        }

        public void setProductoLista(ArrayList<Producto> productoLista) {
            this.productoLista = productoLista;
        }

        @Override
        protected ArrayList<Producto> doInBackground(Void... params) {


            try {
                if (db.getRowCount()==0) {
                    ApiService api = JsonClient.getApiService();
                    Call<ProductoLista> call = api.getProductos();
                    call.enqueue(new Callback<ProductoLista>() {
                        @Override
                        public void onResponse(Call<ProductoLista> call, Response<ProductoLista> response) {
                            if (response.isSuccessful()) {
                                HttpRequestTask.this.setProductoLista(response.body().getProductos());
                                db.grabarProductos(HttpRequestTask.this.getProductoLista());
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductoLista> call, Throwable t) {
                            System.out.println(t.getMessage());
                            t.printStackTrace();
                        }

                    });

                }

                 else{
                    HttpRequestTask.this.setProductoLista( db.getAllProducts());
                }


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);

            }
            return this.getProductoLista();
        }


        @Override
        protected void onPostExecute(ArrayList<Producto> productoLista) {
            productoLista=this.getProductoLista();
            simpleList = (SwipeMenuListView)findViewById(R.id.simpleListView);
            mAdapter = new ProductoAdapter(MainActivity.this ,productoLista);
            simpleList.setAdapter(mAdapter);

        }


    }

};









