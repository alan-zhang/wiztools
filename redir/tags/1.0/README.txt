================================================================================
WizTools.org--URL Shortening Servlet
================================================================================

This is a small servlet to redirect to the actual URL. The URL information is
maintained in a separate text files (UTF-8 encoded). 

--------------------------------------------------------------------------------
Requirement
--------------------------------------------------------------------------------

1. JSP 2.0 compatible container

2. This application is most useful when hidden behind mod-jk--Apache or
   ProxyPass: for super-short URLs. But this is not mandatory.

--------------------------------------------------------------------------------
Source Code and Download
--------------------------------------------------------------------------------

http://wiztools.googlecode.com/

--------------------------------------------------------------------------------
Installation
--------------------------------------------------------------------------------

1. Create folder for data storage.

2. Change the 'configFolder' property in this file to map to the data storage
   directory created in step 1:

   WEB-INF/classes/folder.properties

3. Create data files in the pattern '<shortened-url>.url'. The '<shortened-url>'
   is the shortened URL. The file's first line should be the actual URL.

   Go through the next section to understand the procedure in detail.

--------------------------------------------------------------------------------
Example
--------------------------------------------------------------------------------

Purpose:
  To shorten the URL:
    http://wiztools.org/code/text-encoding-util/README.txt
  To:
    http://localhost/r/a

Steps:

  1. Create the data directory '/opt/redir-data'.
  2. Create the file 'a.url' inside '/opt/redir-data'. The content of this file
     should be the actual URL.
  3. Change the property 'configFolder' in WEB-INF/classes/folder.properties.
  4. Reload the web-application 'r' (redir builds as r.war).
  5. Access the URL: http://localhost/r/a.
     You will be redirected to the actual URL.

--------------------------------------------------------------------------------
License
--------------------------------------------------------------------------------

http://www.apache.org/licenses/LICENSE-2.0

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
By
Subhash Chandran
http://WizTools.org/

