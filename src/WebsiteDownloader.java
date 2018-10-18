import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class WebsiteDownloader
{
    public void downloadWebPage(String urlString)
    {
        List<String> webPage = downloadWebPageIntoMemory(urlString);
        writeWebPageToFile(webPage);
    }

    public void downloadSimplifiedWebPage(String urlString)
    {
        List<String> webPage = downloadWebPageIntoMemory(urlString);
        simplifyWebPage(webPage);
        writeWebPageToFile(webPage);
    }

    public List<String> downloadWebPageIntoMemory(String urlString)
    {
        URL url;
        InputStream stream = null;
        BufferedReader reader;
        String line;
        List<String> webPage = new ArrayList<String>();

        try
        {
            url = new URL(urlString);
            stream = url.openStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null)
            {
                webPage.add(line);
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
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        return webPage;
    }

    public void simplifyWebPage(List<String> webPage)
    {
        for (int i = 0; i < webPage.size(); i++)
        {
            String line = webPage.get(i);
            while (line.contains("<img"))
            {
                int start = line.indexOf("<img");
                int end = line.indexOf(">", start);
                line = line.substring(0, start) + line.substring(end + 1);
            }
            for (int j = 1; j <= 5; j++)
            {
                line = line.replace("<h" + j, "<h6");
                line = line.replace("/h" + j + ">", "/h6>");
            }
            webPage.set(i, line);
        }
    }

    public void writeWebPageToFile(List<String> webPage)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("web_page.html", "UTF-8");
            for (String line : webPage)
            {
                writer.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        finally
        {
            writer.close();
        }
    }
}