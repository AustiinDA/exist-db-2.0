
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;


public class Conexion {
    final static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String[] args) {
        try {
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/prueba";
            final String usuPwd = "acceso";
            final String usu = "admin";

            Collection col = null;

            col = DatabaseManager.getCollection(URI, usu, usuPwd);


            XPathQueryService service = (XPathQueryService)
                    col.getService("XPathQueryService", "1.0");

            String query = "for $em in /EMPLEADOS / EMP_ROW[DEPT_NO = 10] return $em";
            ResourceSet result = service.query(query);


            ResourceIterator iterator = result.getIterator();

            Resource resource;

            if (!iterator.hasMoreResources()) {
                System.out.println("no hay resultados");

            } else {
                while (iterator.hasMoreResources()) {
                    resource = iterator.nextResource();
                    System.out.println(resource.getContent());
                }
            }

            XPathQueryService serv = (XPathQueryService)
                    col.getService("XPathQueryService", "1.0");
            String query2 = "for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=10] return $em";
            ResourceSet resultao = service.query(query);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
