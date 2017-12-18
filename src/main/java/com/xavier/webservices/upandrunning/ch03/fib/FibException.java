package com.xavier.webservices.upandrunning.ch03.fib;

/*
<message name="FibException">
	<part name="fault" element="tns:FibException"/>
</message>
<portType name="RabbitCounter">
	<operation name="countRabbits">
		<input message="tns:countRabbits"/>
		<output message="tns:countRabbitsResponse"/>
		<fault message="tns:FibException" name="FibException"/>
	</operation>
</portType>
 */

/*
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	<S:Header/>
	<S:Body>
		<S:Fault xmlns:ns4="http://www.w3.org/2003/05/soap-envelope" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xml="http://www.w3.org/XML/1998/namespace">
			<faultcode>S:Server</faultcode>
			<faultstring>Neg. arg. not allowed.</faultstring>
			<detail>
				<ns2:FibException xmlns:ns2="http://ch03.fib">
					<faultInfo>-45&lt;0</faultInfo>
					<message>Neg. arg. not allowed.</message>
				</ns2:FibException>
			</detail>
		</S:Fault>
	</S:Body>
</S:Envelope>
 */
/*
 The SOAP fault message includes the  Reason ,  and additional  Detail.
 1.  The  Exception -based class that implements the SOAP fault in Java should have a constructor with two arguments,
        1.1 the first of which gives the reason for the fault
        1.2 the second of which provides additional details about the fault.
  2. The  Exception -based class should define a getFaultInfo method that returns the fault details.
  3. The corresponding SOAP fault message includes the reason and the details.
*/
public class FibException extends Exception {
    private String details;
    public FibException(String reason, String details) {
        super(reason);
        this.details = details;
    }

    public String getFaultInfo() {
        return details;
    }
}
