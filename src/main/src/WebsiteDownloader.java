import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

class WebsiteDownloader
{
    public void downloadWebPage(String urlString)
    {
        URL url;
        InputStream stream = null;
        BufferedReader reader;
        String line;
        PrintWriter writer = null;

        try
        {
            url = new URL(urlString);
            stream = url.openStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            writer = new PrintWriter("web_page.html", "UTF-8");
            while ((line = reader.readLine()) != null)
            {
                writer.println(line);
            }
        }
        catch (MalformedURLException mue)
        {
            mue.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            try
            {
                if (stream != null) stream.close();
                writer.close();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}