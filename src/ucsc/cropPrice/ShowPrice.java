package ucsc.cropPrice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;


public class ShowPrice extends Activity {	
	
   // private static final String ENDPOINT = "http://www.srilanka.lk:9080/services/CropServiceProxy.CropServiceProxyHttpSoap12Endpoint";
	private static final String URL = "https://www.srilanka.lk:9080/services/CropServiceProxy.CropServiceProxyHttpsSoap12Endpoint";
	//private static final String SOAP_ACTION = "http://www.srilanka.lk:9080/services/CropServiceProxy";
	private static final String METHOD_NAME = "getCropDataList";
	private static final String NAMESPACE = "http://schemas.icta.lk/xsd/crop/handler/v1/";
	//private static final String URL = "http://www.srilanka.lk:9080/services/CropServiceProxy";
	
	EditText cropId;
	TextView price;
	Handler WSCropPrice;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        cropId = (EditText) this.findViewById(R.id.cropId);
        price = (TextView) this.findViewById(R.id.price);
        price.setText("agdjagjd");       
	  
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("code", String.valueOf("CNT"));
        
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);	  
        soapEnvelope.setOutputSoapObject(request);
        
        AndroidHttpTransport aht = new AndroidHttpTransport(URL);
        try{
        	aht.call(METHOD_NAME, soapEnvelope);
        	SoapObject results = (SoapObject) soapEnvelope.bodyIn;
        	price.setText(results.getProperty("cropInfo").toString());
        	
        }catch(Exception e){
        	e.printStackTrace();
        }        
        
    }
}
