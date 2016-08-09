import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import java.util.*;
 
public class Client
{
 
    private static Socket socket;
 
    public static void main(String args[])throws IOException
    {
        try
        {
			BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your choice :\n1.upload\n2.Download\t: ");
			int choice=Integer.parseInt(b.readLine());
			
			
			String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
			switch(choice)
			{
			case 1:
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
			
			String file="cx.txt";
			FileInputStream f= new FileInputStream(file);
			
			int ch;
			 String number = "";
			while((ch=f.read())!=-1)
			{
				//count++;
				//ch=f.read();
				number=number+(char)ch;
				System.out.print((char)ch);
			}
           
 
            String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
			//bw.write(file);
			//bw.flush();
			
            System.out.println("Message sent to the server : "+sendMessage);
			
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);
			break;
			}
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}