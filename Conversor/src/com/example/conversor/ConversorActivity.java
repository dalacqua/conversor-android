package com.example.conversor;

import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.conversor.app.AppController;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class ConversorActivity extends Activity {
	
	private Button btnConverter;
	private Spinner spMoeda;
	private EditText edValor;
	private TextView tvResultado;
	private String sigla;
	private String valor;
	private String url;
    private static String TAG = ConversorActivity.class.getSimpleName();
	
	private Valor[] valores = {
			new Valor("AED","United Arab Emirates Dirham (AED)"),
			new Valor("AFN","Afghan Afghani (AFN)"),
			new Valor("ALL","Albanian Lek (ALL)"),
			new Valor("AMD","Armenian Dram (AMD)"),
			new Valor("ANG","Netherlands Antillean Guilder (ANG)"),
			new Valor("AOA","Angolan Kwanza (AOA)"),
			new Valor("ARS","Argentine Peso (ARS)"),
			new Valor("AUD","Australian Dollar (A$)"),
			new Valor("AWG","Aruban Florin (AWG)"),
			new Valor("AZN","Azerbaijani Manat (AZN)"),
			new Valor("BAM","Bosnia-Herzegovina Convertible Mark (BAM)"),
			new Valor("BBD","Barbadian Dollar (BBD)"),
			new Valor("BDT","Bangladeshi Taka (BDT)"),
			new Valor("BGN","Bulgarian Lev (BGN)"),
			new Valor("BHD","Bahraini Dinar (BHD)"),
			new Valor("BIF","Burundian Franc (BIF)"),
			new Valor("BMD","Bermudan Dollar (BMD)"),
			new Valor("BND","Brunei Dollar (BND)"),
			new Valor("BOB","Bolivian Boliviano (BOB)"),
			new Valor("BSD","Bahamian Dollar (BSD)"),
			new Valor("BTC","Bitcoin (฿)"),
			new Valor("BTN","Bhutanese Ngultrum (BTN)"),
			new Valor("BWP","Botswanan Pula (BWP)"),
			new Valor("BYR","Belarusian Ruble (BYR)"),
			new Valor("BZD","Belize Dollar (BZD)"),
			new Valor("CAD","Canadian Dollar (CA$)"),
			new Valor("CDF","Congolese Franc (CDF)"),
			new Valor("CHF","Swiss Franc (CHF)"),
			new Valor("CLF","Chilean Unit of Account (UF) (CLF)"),
			new Valor("CLP","Chilean Peso (CLP)"),
			new Valor("CNH","CNH (CNH)"),
			new Valor("CNY","Chinese Yuan (CN¥)"),
			new Valor("COP","Colombian Peso (COP)"),
			new Valor("CRC","Costa Rican Colón (CRC)"),
			new Valor("CUP","Cuban Peso (CUP)"),
			new Valor("CVE","Cape Verdean Escudo (CVE)"),
			new Valor("CZK","Czech Republic Koruna (CZK)"),
			new Valor("DEM","German Mark (DEM)"),
			new Valor("DJF","Djiboutian Franc (DJF)"),
			new Valor("DKK","Danish Krone (DKK)"),
			new Valor("DOP","Dominican Peso (DOP)"),
			new Valor("DZD","Algerian Dinar (DZD)"),
			new Valor("EGP","Egyptian Pound (EGP)"),
			new Valor("ERN","Eritrean Nakfa (ERN)"),
			new Valor("ETB","Ethiopian Birr (ETB)"),
			new Valor("EUR","Euro (€)"),
			new Valor("FIM","Finnish Markka (FIM)"),
			new Valor("FJD","Fijian Dollar (FJD)"),
			new Valor("FKP","Falkland Islands Pound (FKP)"),
			new Valor("FRF","French Franc (FRF)"),
			new Valor("GBP","British Pound Sterling (£)"),
			new Valor("GEL","Georgian Lari (GEL)"),
			new Valor("GHS","Ghanaian Cedi (GHS)"),
			new Valor("GIP","Gibraltar Pound (GIP)"),
			new Valor("GMD","Gambian Dalasi (GMD)"),
			new Valor("GNF","Guinean Franc (GNF)"),
			new Valor("GTQ","Guatemalan Quetzal (GTQ)"),
			new Valor("GYD","Guyanaese Dollar (GYD)"),
			new Valor("HKD","Hong Kong Dollar (HK$)"),
			new Valor("HNL","Honduran Lempira (HNL)"),
			new Valor("HRK","Croatian Kuna (HRK)"),
			new Valor("HTG","Haitian Gourde (HTG)"),
			new Valor("HUF","Hungarian Forint (HUF)"),
			new Valor("IDR","Indonesian Rupiah (IDR)"),
			new Valor("IEP","Irish Pound (IEP)"),
			new Valor("ILS","Israeli New Sheqel (₪)"),
			new Valor("INR","Indian Rupee (Rs.)"),
			new Valor("IQD","Iraqi Dinar (IQD)"),
			new Valor("IRR","Iranian Rial (IRR)"),
			new Valor("ISK","Icelandic Króna (ISK)"),
			new Valor("ITL","Italian Lira (ITL)"),
			new Valor("JMD","Jamaican Dollar (JMD)"),
			new Valor("JOD","Jordanian Dinar (JOD)"),
			new Valor("JPY","Japanese Yen (¥)"),
			new Valor("KES","Kenyan Shilling (KES)"),
			new Valor("KGS","Kyrgystani Som (KGS)"),
			new Valor("KHR","Cambodian Riel (KHR)"),
			new Valor("KMF","Comorian Franc (KMF)"),
			new Valor("KPW","North Korean Won (KPW)"),
			new Valor("KRW","South Korean Won (₩)"),
			new Valor("KWD","Kuwaiti Dinar (KWD)"),
			new Valor("KYD","Cayman Islands Dollar (KYD)"),
			new Valor("KZT","Kazakhstani Tenge (KZT)"),
			new Valor("LAK","Laotian Kip (LAK)"),
			new Valor("LBP","Lebanese Pound (LBP)"),
			new Valor("LKR","Sri Lankan Rupee (LKR)"),
			new Valor("LRD","Liberian Dollar (LRD)"),
			new Valor("LSL","Lesotho Loti (LSL)"),
			new Valor("LTL","Lithuanian Litas (LTL)"),
			new Valor("LVL","Latvian Lats (LVL)"),
			new Valor("LYD","Libyan Dinar (LYD)"),
			new Valor("MAD","Moroccan Dirham (MAD)"),
			new Valor("MDL","Moldovan Leu (MDL)"),
			new Valor("MGA","Malagasy Ariary (MGA)"),
			new Valor("MKD","Macedonian Denar (MKD)"),
			new Valor("MMK","Myanmar Kyat (MMK)"),
			new Valor("MNT","Mongolian Tugrik (MNT)"),
			new Valor("MOP","Macanese Pataca (MOP)"),
			new Valor("MRO","Mauritanian Ouguiya (MRO)"),
			new Valor("MUR","Mauritian Rupee (MUR)"),
			new Valor("MVR","Maldivian Rufiyaa (MVR)"),
			new Valor("MWK","Malawian Kwacha (MWK)"),
			new Valor("MXN","Mexican Peso (MX$)"),
			new Valor("MYR","Malaysian Ringgit (MYR)"),
			new Valor("MZN","Mozambican Metical (MZN)"),
			new Valor("NAD","Namibian Dollar (NAD)"),
			new Valor("NGN","Nigerian Naira (NGN)"),
			new Valor("NIO","Nicaraguan Córdoba (NIO)"),
			new Valor("NOK","Norwegian Krone (NOK)"),
			new Valor("NPR","Nepalese Rupee (NPR)"),
			new Valor("NZD","New Zealand Dollar (NZ$)"),
			new Valor("OMR","Omani Rial (OMR)"),
			new Valor("PAB","Panamanian Balboa (PAB)"),
			new Valor("PEN","Peruvian Nuevo Sol (PEN)"),
			new Valor("PGK","Papua New Guinean Kina (PGK)"),
			new Valor("PHP","Philippine Peso (Php)"),
			new Valor("PKG","PKG (PKG)"),
			new Valor("PKR","Pakistani Rupee (PKR)"),
			new Valor("PLN","Polish Zloty (PLN)"),
			new Valor("PYG","Paraguayan Guarani (PYG)"),
			new Valor("QAR","Qatari Rial (QAR)"),
			new Valor("RON","Romanian Leu (RON)"),
			new Valor("RSD","Serbian Dinar (RSD)"),
			new Valor("RUB","Russian Ruble (RUB)"),
			new Valor("RWF","Rwandan Franc (RWF)"),
			new Valor("SAR","Saudi Riyal (SAR)"),
			new Valor("SBD","Solomon Islands Dollar (SBD)"),
			new Valor("SCR","Seychellois Rupee (SCR)"),
			new Valor("SDG","Sudanese Pound (SDG)"),
			new Valor("SEK","Swedish Krona (SEK)"),
			new Valor("SGD","Singapore Dollar (SGD)"),
			new Valor("SHP","Saint Helena Pound (SHP)"),
			new Valor("SLL","Sierra Leonean Leone (SLL)"),
			new Valor("SOS","Somali Shilling (SOS)"),
			new Valor("SRD","Surinamese Dollar (SRD)"),
			new Valor("STD","São Tomé and Príncipe Dobra (STD)"),
			new Valor("SVC","Salvadoran Colón (SVC)"),
			new Valor("SYP","Syrian Pound (SYP)"),
			new Valor("SZL","Swazi Lilangeni (SZL)"),
			new Valor("THB","Thai Baht (฿)"),
			new Valor("TJS","Tajikistani Somoni (TJS)"),
			new Valor("TMT","Turkmenistani Manat (TMT)"),
			new Valor("TND","Tunisian Dinar (TND)"),
			new Valor("TOP","Tongan Paʻanga (TOP)"),
			new Valor("TRY","Turkish Lira (TRY)"),
			new Valor("TTD","Trinidad and Tobago Dollar (TTD)"),
			new Valor("TWD","New Taiwan Dollar (NT$)"),
			new Valor("TZS","Tanzanian Shilling (TZS)"),
			new Valor("UAH","Ukrainian Hryvnia (UAH)"),
			new Valor("UGX","Ugandan Shilling (UGX)"),
			new Valor("USD","US Dollar ($)"),
			new Valor("UYU","Uruguayan Peso (UYU)"),
			new Valor("UZS","Uzbekistan Som (UZS)"),
			new Valor("VEF","Venezuelan Bolívar (VEF)"),
			new Valor("VND","Vietnamese Dong (₫)"),
			new Valor("VUV","Vanuatu Vatu (VUV)"),
			new Valor("WST","Samoan Tala (WST)"),
			new Valor("XAF","CFA Franc BEAC (FCFA)"),
			new Valor("XCD","East Caribbean Dollar (EC$)"),
			new Valor("XDR","Special Drawing Rights (XDR)"),
			new Valor("XOF","CFA Franc BCEAO (CFA)"),
			new Valor("XPF","CFP Franc (CFPF)"),
			new Valor("YER","Yemeni Rial (YER)"),
			new Valor("ZAR","South African Rand (ZAR)"),
			new Valor("ZMK","Zambian Kwacha (1968–2012) (ZMK)"),
			new Valor("ZMW","Zambian Kwacha (ZMW)"),
			new Valor("ZWL","Zimbabwean Dollar (2009) (ZWL)")
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversor);

		spMoeda = (Spinner) findViewById(R.id.spMoeda);
		edValor = (EditText) findViewById(R.id.tvValor);
		tvResultado  = (TextView) findViewById(R.id.tvResultado);
		
		MySpinnerAdapter adapter = new MySpinnerAdapter(ConversorActivity.this, android.R.layout.simple_spinner_item, valores);
		spMoeda.setAdapter(adapter);
		spMoeda.setOnItemSelectedListener(onItemSelectedListener);
		

		
		btnConverter = (Button) findViewById(R.id.btnConverter);
		btnConverter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				valor = edValor.getText().toString();
				// url que retorna o JSONObject
				url = "http://rate-exchange.appspot.com/currency?from="+ sigla +"&to=BRL&q="+ valor;
				// metodo que faz requisicao JSON
                makeJsonRequest();
			}
		});	
	}
	
	OnItemSelectedListener onItemSelectedListener =
			new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					Valor obj = (Valor)(parent.getItemAtPosition(position));
					sigla = obj.getSigla();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {}
		};

		
	    private void makeJsonRequest() {
	    	 
	        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
	                url, null, new Response.Listener<JSONObject>() {
	     
	                    @Override
	                    public void onResponse(JSONObject response) {
	                        Log.d(TAG, response.toString());
	     
	                        try {
	                        	String jsonResponse = response.getString("v");
	                        	Double value = Double.parseDouble(jsonResponse);
	                        	DecimalFormat df = new DecimalFormat("0.00");  
	                        	jsonResponse = df.format(value);
	                        	
	                        	tvResultado.setText(jsonResponse + " reais");
	                        } catch (JSONException e) {
	                            e.printStackTrace();
	                            Toast.makeText(getApplicationContext(),
	                                    "Error: " + e.getMessage(),
	                                    Toast.LENGTH_LONG).show();
	                        }
	                    }
	                }, new Response.ErrorListener() {
	     
	                    @Override
	                    public void onErrorResponse(VolleyError error) {
	                        VolleyLog.d(TAG, "Error: " + error.getMessage());
	                        Toast.makeText(getApplicationContext(),
	                                error.getMessage(), Toast.LENGTH_SHORT).show();
	                    }
	                });
	     
	        // Adding request to request queue
	        AppController.getInstance().addToRequestQueue(jsonObjReq);
	    }
	
}