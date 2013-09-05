/*   This file is part of lanSimulation.
 *
 *   lanSimulation is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   lanSimulation is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with lanSimulation; if not, write to the Free Software
 *   Foundation, Inc. 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *   Copyright original Java version: 2004 Bart Du Bois, Serge Demeyer
 *   Copyright C++ version: 2006 Matthias Rieger, Bart Van Rompaey
 */
package lanSimulation.internals;

import lanSimulation.Network;

import java.io.IOException;
import java.io.Writer;

/**
 * A <em>Node</em> represents a single Node in a Local Area Network (LAN).
 * Several types of Nodes exist.
 */
public class Node {
    // enumeration constants specifying all legal node types
    /**
     * A node with type NODE has only basic functionality.
     */
    public static final byte NODE = 0;
    /**
     * A node with type WORKSTATION may initiate requests on the LAN.
     */
    public static final byte WORKSTATION = 1;
    /**
     * A node with type PRINTER may accept packages to be printed.
     */
    public static final byte PRINTER = 2;
    /**
     * Holds the type of the Node.
     */
    public byte type;
    /**
     * Holds the name of the Node.
     */
    public String name;
    /**
     * Holds the next Node in the token ring architecture.
     *
     * @see lanSimulation.internals.Node
     */
    public Node nextNode;

    /**
     * Construct a <em>Node</em> with given #type and #name.
     * <p>
     * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
     * </p>
     */
    public Node(byte _type, String _name) {
        assert (_type >= NODE) & (_type <= PRINTER);
        type = _type;
        name = _name;
        nextNode = null;
    }

    /**
     * Construct a <em>Node</em> with given #type and #name, and which is linked
     * to #nextNode.
     * <p>
     * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
     * </p>
     */
    public Node(byte _type, String _name, Node _nextNode) {
        assert (_type >= NODE) & (_type <= PRINTER);
        type = _type;
        name = _name;
        nextNode = _nextNode;
    }

    public boolean printDocument(Packet document, Writer report, Network network) {
        String author = "Unknown";
        String title = "Untitled";
        int startPos, endPos;

        if (type == PRINTER) {
            try {
                String jobType;
                if (document.message.startsWith("!PS")) {
                    jobType = "Postscript";
                    startPos = document.message.indexOf("author:");
                    if (startPos >= 0) {
                        endPos = document.message.indexOf(".", startPos + 7);
                        if (endPos < 0) {
                            endPos = document.message.length();
                        }

                        author = document.message.substring(startPos + 7,
                                endPos);
                    }

                    startPos = document.message.indexOf("title:");
                    if (startPos >= 0) {
                        endPos = document.message.indexOf(".", startPos + 6);
                        if (endPos < 0) {
                            endPos = document.message.length();
                        }
                        title = document.message
                                .substring(startPos + 6, endPos);
                    }

                    network.firstNode.writeAccountingReport(report, author, title, jobType, network);
                } else {
                    jobType = "ASCII Print";
                    title = "ASCII DOCUMENT";
                    if (document.message.length() >= 16) {
                        author = document.message.substring(8, 16);
                    }

                    network.firstNode.writeAccountingReport(report, author, title, jobType, network);
                }

            } catch (IOException exc) {
                // just ignore
            }
            return true;
        } else {
            try {
                report
                        .write(">>> Destination is not a printer, print job canceled.\n\n");
                report.flush();
            } catch (IOException exc) {
                // just ignore
            }
            return false;
        }
    }

    private void writeAccountingReport(Writer report, String author, String title, String jobType, Network network) throws IOException {
        report.write("\tAccounting -- author = '");
        report.write(author);
        report.write("' -- title = '");
        report.write(title);
        report.write("'\n");
        this.writeJobDelivered(report, jobType);
        report.flush();
    }

    private void writeJobDelivered(Writer report, String jobType) throws IOException {
        report.write(">>> " + jobType + " job delivered.\n\n");
    }

    /**
     * The #receiver is requested to broadcast a message to all nodes. Therefore
     * #receiver sends a special broadcast packet across the token ring network,
     * which should be treated by all nodes.
     * <p>
     * <strong>Precondition:</strong> consistentNetwork();
     * </p>
     *
     * @param report  Stream that will hold a report about what happened when
     *                handling the request.
     * @param network
     * @return Answer #true when the broadcast operation was successful and
     *         #false otherwise
     */
    public boolean requestBroadcast(Writer report, Network network) {
        assert network.consistentNetwork();

        try {
            report.write("Broadcast Request\n");
        } catch (IOException exc) {
            // just ignore
        }

        Node currentNode = this;
        Packet packet = new Packet("BROADCAST", name, name);
        do {
            try {
                report.write("\tNode '");
                report.write(currentNode.name);
                report.write("' accepts broadcast packet.\n");
                this.writeNodePacketPasses(report, currentNode);
            } catch (IOException exc) {
                // just ignore
            }

            currentNode = currentNode.nextNode;
        } while (!packet.destination.equals(currentNode.name));

        try {
            report.write(">>> Broadcast traveled whole token ring.\n\n");
        } catch (IOException exc) {
            // just ignore
        }

        return true;
    }

    public void writeNodePacketPasses(Writer report, Node node) throws IOException {
        report.write("\tNode '");
        report.write(node.name);
        report.write("' passes packet on.\n");
        report.flush();
    }

    /**
	 * The #receiver is requested by #workstation to print #document on
	 * #printer. Therefore #receiver sends a packet across the token ring
	 * network, until either (1) #printer is reached or (2) the packet traveled
	 * complete token ring.
	 * <p>
	 * <strong>Precondition:</strong> consistentNetwork() &
	 * hasWorkstation(workstation);
	 * </p>
	 *
	 *
     * @param workstation
	 *            Name of the workstation requesting the service.
	 * @param document
	 *            Contents that should be printed on the printer.
	 * @param printer
	 *            Name of the printer that should receive the document.
	 * @param report
	 *            Stream that will hold a report about what happened when
	 *            handling the request.
	 * @param network
     * @return Answer #true when the print operation was successful and #false
	 *         otherwise
	 */
	public boolean requestWorkstationPrintsDocument(String workstation,
                                                    String document, String printer, Writer report, Network network) {
		assert network.consistentNetwork() & network.hasWorkstation(workstation);

		try {
			report.write("'");
			report.write(workstation);
			report.write("' requests printing of '");
			report.write(document);
			report.write("' on '");
			report.write(printer);
			report.write("' ...\n");
		} catch (IOException exc) {
			// just ignore
		}

		boolean result;
		Node startNode, currentNode;
		Packet packet = new Packet(document, workstation, printer);

		startNode = network.workstations.get(workstation);

		try {
            this.writeNodePacketPasses(report, startNode);
		} catch (IOException exc) {
			// just ignore
		}

		currentNode = startNode.nextNode;
		while ((!packet.destination.equals(currentNode.name))
				& (!packet.origin.equals(currentNode.name))) {
			try {
                this.writeNodePacketPasses(report, currentNode);
			} catch (IOException exc) {
				// just ignore
			}

			currentNode = currentNode.nextNode;
		}

		if (packet.destination.equals(currentNode.name)) {
			result = currentNode.printDocument(packet, report, network);
		} else {
			try {
				report
						.write(">>> Destination not found, print job canceled.\n\n");
				report.flush();
			} catch (IOException exc) {
				// just ignore
			}

			result = false;
		}

		return result;
	}
}