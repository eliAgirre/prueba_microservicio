package com.ibm;


import com.ibm.models.Proveedor;

import java.io.IOException;
import java.util.List;

public class JsonToObjectsCreator extends BaseJsonToObjectsCreator {

    public List<Proveedor> proveedoresListIdCliente5() throws IOException {
        return getObjectListFromFile("/json/proveedoresList_idCliente5.json", com.ibm.models.Proveedor.class);
    }

    public List<Proveedor> proveedoresListIdCliente6() throws IOException {
        return getObjectListFromFile("/json/proveedoresList_idCliente6.json", com.ibm.models.Proveedor.class);
    }

    public List<Proveedor> proveedoresListIdCliente7() throws IOException {
        return getObjectListFromFile("/json/proveedoresList_idCliente7.json", com.ibm.models.Proveedor.class);
    }

    public List<Proveedor> proveedoresListIdCliente8() throws IOException {
        return getObjectListFromFile("/json/proveedoresList_idCliente8.json", com.ibm.models.Proveedor.class);
    }

    public List<Proveedor> proveedoresListIdCliente9() throws IOException {
        return getObjectListFromFile("/json/proveedoresList_idCliente9.json", com.ibm.models.Proveedor.class);
    }

}
