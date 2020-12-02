package hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.HColumnDescriptor;

public class createhbasetable {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(config);

        try {
            HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

            // create the table...
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("users"));
            // ... with two column families
            tableDescriptor.addFamily(new HColumnDescriptor("personalinfo"));
            tableDescriptor.addFamily(new HColumnDescriptor("skills"));

            if (!admin.tableExists(tableDescriptor.getTableName())) {
                admin.createTable(tableDescriptor);
                System.out.println("Created table " + tableDescriptor.getTableName() + ".");
            }
            else {
                System.out.println("Table already exists.");
            }

        } finally {
            connection.close();
        }

    }

}
