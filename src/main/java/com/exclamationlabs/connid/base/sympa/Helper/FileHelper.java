package com.exclamationlabs.connid.base.sympa.Helper;


import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * This class contains File Handling Helper methods.
 *
 * @author Stephen Fox
 */
public class FileHelper
{
    /**
     * Copies the contents of a resource from an URL to a File.
     *
     * @param srcURL
     *            The URL name that is the source of the data
     * @param filename
     *            The file to copy the information into.
     */
    public static void copyURLToFile(String srcURL, String filename)
            throws IOException
    {
        URL url = new URL(srcURL);
        copyURLToFile(url, filename);
    }

    /**
     * Copies the contents of a resource from a URL to a File.
     *
     * @param url
     *            The URL source
     * @param filename
     *            The filename of the destination
     */
    public static void copyURLToFile(URL url, String filename) throws IOException
    {
        byte[] buf = new byte[1];
        /*
         * get an input stream for the URL
         */
        InputStream in = url.openStream();

        /*
         * get an output stream for the file
         */
        FileOutputStream out = new FileOutputStream(filename);

        /*
         * Move the data. OK maybe buffered IO might improve performance.
         */
        while ((in.read(buf, 0, 1)) != -1)
        {
            out.write(buf, 0, 1);
        }
        /*
         * Close the streams
         */
        in.close();
        out.close();
    }

    /**
     * Copies the contents of a Byte array to a File.
     *
     * @param data
     *            The buffer source
     * @param filename
     *            The filename of the destination
     */
    public static void copyBufferToFile(byte[] data, String filename) throws IOException
    {
        /*
         * get an output stream for the file
         */
        FileOutputStream out = new FileOutputStream(filename);

        /*
         * Move the data. OK maybe buffered IO might improve performance.
         */
        out.write(data);
        /*
         * Close the streams
         */
        out.close();
    }


    /**
     * Returns an input stream for a file in the class path.
     *
     * @return InputStream for reading the resource
     * @param  fileName the name of the file to locate
     */
    public static InputStream getResourceAsStream(String fileName)
    {
        InputStream in = null;
        try
        {
            ClassLoader cldr = Thread.currentThread().getContextClassLoader();
            in = cldr.getResourceAsStream(fileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * Loads the contents of the given file into a byte array.
     *
     * @param srcFileName
     *            The file to load
     * @return byte[] byte array with the file contents
     */
    public static byte[] loadFileToBuffer(String srcFileName) throws IOException
    {
        byte[] buf = new byte[4096];
        byte[] data = null;
        byte[] temp = null;
        int iCount = 0;
        int iTotal = 0;

        /*
         * get an input stream for the URL
         */
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(srcFileName), 20480);

        /*
         * load the data. Buffered IO might improve performance.
         */
        while ((iCount = in.read(buf, 0, buf.length)) != -1)
        {
            if (iTotal == 0)
            {
                data = new byte[iCount];
                System.arraycopy(buf, 0, data, 0, iCount);
                iTotal = iCount;
            }
            else
            {
                /*
                 * Append the buffer to the data
                 */
                temp = new byte[iCount + iTotal];
                System.arraycopy(data, 0, temp, 0, iTotal);
                System.arraycopy(buf, 0, temp, iTotal, iCount);
                data = temp;
                iTotal = iTotal + iCount;
            }
        }
        /*
         * Close the stream
         */
        in.close();
        return data;
    }

    /**
     * Load Properties from a property File.
     *
     * @param propertyFileName the property file name
     * @return java.util.Properties
     */
    public static Properties loadProperties(String propertyFileName)
    {
        Properties theProps = null;
        try
        {
            FileInputStream inStream = new FileInputStream(propertyFileName);
            theProps = new Properties();
            theProps.load(inStream);
            inStream.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (IOException ioex )
        {
            ioex.printStackTrace();
        }
        return theProps;
    }

    /**
     * Load Properties from a property File when the file is in the classpath.
     *
     * @param propertyFileName
     *           the property file name
     * @return A java.util.Properties object containing the data
     */
    public static Properties loadPropertiesFromResource(String propertyFileName)
    {
        Properties theProps = null;

        try
        {
            InputStream in = getResourceAsStream(propertyFileName);
            theProps = new Properties();
            theProps.load(in);
            in.close();
        }
        catch (IOException ioe)
        {
            theProps = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return theProps;
    }
    /**
     * Loads the contents of an input stream into a byte array.
     *
     * @param stream
     *            the InputStream to load
     * @return byte[] Byte array with the contents of the file
     */
    public static byte[] loadInputStreamToBuffer(InputStream stream) throws IOException
    {
        byte[] buf = new byte[4096];
        byte[] data = null;
        byte[] temp = null;
        int iCount = 0;
        int iTotal = 0;

        /*
         * get an input stream for the URL
         */
        BufferedInputStream in = new BufferedInputStream(stream, 20480);

        /*
         * Move the data. OK maybe buffered IO might improve performance.
         */
        while ((iCount = in.read(buf, 0, buf.length)) != -1)
        {
            if (iTotal == 0)
            {
                data = new byte[iCount];
                System.arraycopy(buf, 0, data, 0, iCount);
                iTotal = iCount;
            }
            else
            {
                /*
                 * Append the buffer to the data
                 */
                temp = new byte[iCount + iTotal];
                System.arraycopy(data, 0, temp, 0, iTotal);
                System.arraycopy(buf, 0, temp, iTotal, iCount);
                data = temp;
                iTotal = iTotal + iCount;
            }
        }
        /*
         * Close the stream
         */
        in.close();
        return data;
    }

    /**
     * Loads the contents of the given URL into a byte array of a given length.
     *
     * @param url
     *            the name of the resource
     * @param maxLength
     *              the maximum length of the byte array returned
     * @return byte[] Byte array with the contents of the file
     */
    public static byte[] loadURLToBuffer(URL url, int maxLength) throws IOException
    {
        byte[] buf = new byte[maxLength];
        byte[] data = null;
        int iCount = 0;

        /* get an input stream for the URL */
        BufferedInputStream in = new BufferedInputStream(url.openStream(), 20480);

        /* Get the Data */
        iCount = in.read(buf, 0, buf.length);
        if ( iCount != -1 )
        {
            data = new byte[iCount];
            System.arraycopy(buf, 0, data, 0, iCount);
        }

        /* Close the stream */
        in.close();

        return data;
    }

    /**
     * Loads the contents of the given URL into a byte array of a given length.
     *
     * @param fileName
     *            the name of the resource
     * @param charset
     *              the character set used to convert the input to a string
     * @return String containing with the contents of the file
     */
    public static String loadResourceToString(String fileName, Charset charset) throws IOException
    {
        String output = null;
        try
        {
            InputStream in = FileHelper.getResourceAsStream(fileName);
            byte[] data = FileHelper.loadInputStreamToBuffer(in);
            output = new String(data, charset);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return output;
    }

    /**
     * Loads the contents of the given URL into an array list of strings.
     * Each string represents a line in the loaded resource.
     *
     * @param connection
     *            the name of the resource
     * @return ArrayList of String with the contents of the file
     */
    public static ArrayList<String> loadURLToStrings(URLConnection connection, int maxLines) throws IOException
    {
        String inputLine;
        ArrayList<String> lines = new ArrayList<String>();
        connection.connect();
        int lineNumber = 0;

        /*
         * get an input stream for the URL
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        /*
         * Get the data.
         */
        while ((inputLine = in.readLine()) != null && (lineNumber < maxLines || maxLines == -1) )
        {
            lines.add(inputLine);
            lineNumber++;
        }
        /*
         * Close the stream
         */
        in.close();
        return lines;
    }
    /**
     * Loads the contents of the given input stream into an array list of strings.
     * Each string represents a line in the loaded resource.
     *
     * @param stream
     *            the input stream to be parsed
     *
     * @return ArrayList of String with the contents of the stream
     */
    public static ArrayList<String> loadInputStreamToStrings(InputStream stream, int maxLines) throws IOException
    {
        String inputLine;
        ArrayList<String> lines = new ArrayList<String>();
        int lineNumber = 0;

        /*
         * get an input stream for the URL
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        /*
         * Get the data.
         */
        while ((inputLine = in.readLine()) != null && (lineNumber < maxLines || maxLines == -1) )
        {
            lines.add(inputLine);
            lineNumber++;
        }
        /*
         * Close the stream
         */
        in.close();
        return lines;
    }

    /**
     * FileHelper constructor.
     */
    public FileHelper()
    {
        super();
    }
}
