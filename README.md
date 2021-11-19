# Sympa Connector for Midpoint


# 1	Overview

This software is licensed under the terms of [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

This connector uses the Trusted Application interface to interact with a Sympa SOAP service. This interface is described in the Sympa Administration Manual [here](https://sympa-community.github.io/manual/customize/soap-api.html#trust-remote-applications)

The current version integrates with a Sympa SOAP service to allow a Midpoint instance to perform the following operations:



* Create A List
* Close A List
* Get all lists
* Get one list by list name or list address.


# 2	Getting Started



* After installing the Sympa Server you will need to create or modify the _trusted_application.conf_ file to produce an application name, and an application password that can be used to configure the connection.
* Download the latest version of the connector from this repository.
* Install and configure the connector into your midpoint instance
* Create a resource for each Sympa instance that you wish to control


# 3	Midpoint Setup



* Copy the compiled connector jar file to the _icf-connectors_ subdirectory of your Midpoint instance. This is the connector library folder.
* Restart midPoint.
* Within Midpoint create a resource type definition for each Sympa Instance you want to control. Each instance will need its own resource configuration. Make sure to set the fully qualified file location of the configuration file.


# 4	Connector Configuration

The connector can be configured by entering the parameters in a property file or by entering the parameters on the Resource Configuration Page.

The following configuration properties are necessary for the connector to operate. No value can be blank or null. See [https://github.com/ExclamationLabs/connector-sympa/blob/main/src/main/resources/sympa-config-example.properties](https://github.com/ExclamationLabs/connector-sympa/blob/main/src/main/resources/sympa-config-example.properties) for an example configuration file.



* **app.name** is the trusted application name specified in the target Sympa server's trusted_applications.conf file.
* **app.password** is the trusted application password specified by the Sympa server's administrator and whose md5 hash is stored in the trusted_applications.conf file.
* **list.master** is the email address of the list master who is permitted to perform operations executed by the connector. The Sympa administrator should grant the necessary permissions to the email address specified here.
* **sympa.domain.url** is the url of the Sympa Service you which to control. This url endpoint is typically specified as the port location in the service WSDL.
* **sympa.domain.wsdl** is the url of the Sympa server's WSDL file. This url should contain the same fully qualified host name as the sympa.domain.url property
* **CONNECTOR_BASE_CONFIGURATION_ACTIVE** This configuration item originates from the base connector and should be set to Y


# 5	Connector Schema

The connector schema consists of the following attributes.


<table>
  <tr>
   <td>Attribute
   </td>
   <td>IN
   </td>
   <td>OUT
   </td>
   <td>Required
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>LIST_NAME
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>A unique name in the Server domain. Required to create a list
   </td>
  </tr>
  <tr>
   <td>LIST_ADDRESS
   </td>
   <td>Yes
   </td>
   <td>No
   </td>
   <td>No
   </td>
   <td>The email address of a list. Returned on Create or Get requests
   </td>
  </tr>
  <tr>
   <td>DESCRIPTION
   </td>
   <td>No
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Required to create a list
   </td>
  </tr>
  <tr>
   <td>SUBJECT
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Required to create a list. Always returned 
   </td>
  </tr>
  <tr>
   <td>TEMPLATE
   </td>
   <td>No
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Required to create a list
   </td>
  </tr>
  <tr>
   <td>TOPICS
   </td>
   <td>No
   </td>
   <td>Yes
   </td>
   <td>Yes
   </td>
   <td>Required to create a list. The topics must exist in the SYMPA server <strong>topics.conf</strong> file. 
   </td>
  </tr>
  <tr>
   <td>DOMAIN
   </td>
   <td>Yes
   </td>
   <td>No
   </td>
   <td>No
   </td>
   <td>The server’s mail domain. Returned on Create or Get requests
   </td>
  </tr>
</table>



# 6	Connector Operations

When the **Create List** operation is executed all required attributes must be specified or the operation will fail. If the LIST_NAME already exists on the Sympa server the operation will fail. Once a list has been created it cannot be updated through the connector interface. However the List owner may manually change some attributes such as subject, topics, or description through a SYMPA web interface.

The **GetAll** operation is executed on all queries. The connector returns only the attributes specified in the **IN** column of the schema table.

The **GetOne** operation is executed on a single item lookup. Again only the attributes in the **IN** column are returned.

The **Close List** operation is executed to delete a List. Once a list has been closed it cannot be re-created unless the SYMPA administrator manually purges it’s archive record.  
