

# SYMPA Connector for Midpoint


# 1	Overview

This software is licensed under the terms of [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

This connector uses the Trusted Application interface to interact with a SYMPA SOAP service. This interface is described in the [SYMPA Administration Manual](https://sympa-community.github.io/manual/customize/soap-api.html#trust-remote-applications).

The current version integrates with a SYMPA SOAP service to allow a Midpoint instance to perform the following operations:



* Create A List
* Close A List
* Get all lists
* Get one list by list name or list address.

The connector supports the management of Mailing List owners, moderators, and subscribers through an external data source as described below.


# 2	Getting Started



* ​​After installing the SYMPA Server you will need to create or modify the _trusted_application.conf_ file to produce an application name, and an application password that can be used to configure the connection.
* Download the latest version of the connector from this repository.
* Install and configure the connector into your midpoint instance
* Create a resource for each SYMPA instance that you wish to control


# 3	Midpoint Setup



* Copy the compiled connector jar file to the _icf-connectors_ subdirectory of your Midpoint instance. This is the connector library folder.
* Restart midPoint.
* Within Midpoint, create a resource type definition for each SYMPA Instance you want to control. Each instance will need its own resource configuration.
* We have included an example [schema extension](https://github.com/ExclamationLabs/connector-sympa/blob/main/artifacts/schema/sympa_org_extension.xsd) to support mapping of SYMPA attributes to Midpoint Organizations


# 4	SYMPA Setup

This connector is designed to work with SYMPA servers that use [SYMPA external Data Sources](https://sympa-community.github.io/manual/customize/data-sources.html) such as LDAP groups to determine the Owners, Moderators, and Subscribers of the Mailing Lists that are created.

The connector relies on the [SYMPA List Creation Template](https://sympa-community.github.io/manual/customize/basics-templates.html) specified at the time of creation to associate the LIST_NAME with its Owners, Moderators, and Subscribers.

You will also need to customize the SYMPA topics.conf file to support the TOPICS attribute that must be specified when you create a list.


# 5	Connector Configuration

The connector can be configured by entering the parameters in a property file or by entering the parameters on the Resource Configuration Page.

The following configuration properties are necessary for the connector to operate. No value can be blank or null. See [https://github.com/ExclamationLabs/connector-sympa/blob/main/src/main/resources/sympa-config-example.properties](https://github.com/ExclamationLabs/connector-sympa/blob/main/src/main/resources/sympa-config-example.properties) for an example configuration file.



* **custom.appName** is the trusted application name specified in the target SYMPA server's trusted_applications.conf file.
* **custom.appPassword** is the trusted application password specified by the SYMPA server's administrator and whose md5 hash is stored in the trusted_applications.conf file.
* **custom.listMaster** is the email address of the list master who is permitted to perform operations executed by the connector. The SYMPA administrator should grant the necessary permissions to the email address specified here.
* **custom.sympaDomainURL** is the url of the SYMPA Service you which to control. This url endpoint is typically specified as the port location in the service WSDL.
* **custom.sympaDomainWSDL** is the url of the SYMPA server's WSDL file. This url should contain the same fully qualified host name as the sympa.domain.url property

# 6	Connector Schema

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
   <td>A unique name in the Server’s DOMAIN. Required to create a list
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
   <td>The email address of a list. Returned on Create or Get requests. This email address is LIST_NAME@DOMAIN. 
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
   <td>Required to create a list. Specifies a list creation template that associates the LIST_NAME with owners, moderators, and subscribers.
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
   <td>Required to create a list. The topics specified by this attribute must match ones in the SYMPA server <strong>topics.conf</strong> file. 
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
  <tr>
   <td>HOMEPAGE
   </td>
   <td>Yes
   </td>
   <td>No
   </td>
   <td>No
   </td>
   <td>The lists home page URL. Returned on Create or Get requests
   </td>
  </tr>
</table>



# 7	Connector Operations

When the **Create List** operation is executed all required attributes must be specified or the operation will fail. If the LIST_NAME already exists on the SYMPA server the operation will fail. Once a list has been created it cannot be updated through the connector interface. However the List owner may manually change some attributes such as subject, topics, or description through a SYMPA web interface.

The **GetAll** operation is executed on all queries. The connector returns only the attributes specified in the **IN** column of the schema table.

The **GetOne** operation is executed on a single item lookup. Again only the attributes in the **IN** column are returned.

The **Close List** operation is executed to delete a List. Once a list has been closed it cannot be re-created unless the SYMPA administrator manually purges it’s archive record.  
