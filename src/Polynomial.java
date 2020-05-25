import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private Node root;
    private int length;


    public Polynomial() {
        length = 1;
        root = new Node(0, 0, null);

    }

    public Polynomial(int[] coefficients) {
        if (coefficients.length == 1 && coefficients[0] == 0) {
            length = 1;
            root = new Node(0, 0, null);
        } else {
            length = 0;
            Node prevNode = null;
            for (int i = 0; i < coefficients.length; i++) {
                if (coefficients[i] == 0) {
                    continue; //Do nothing
                } else {
                    Node node = new Node(coefficients[i], i, prevNode);
                    prevNode = node;
                    root = node;
                    length++;
                }
            }
        }

    }


    Polynomial add(Polynomial pol2) {
        int orderPol2 = pol2.getOrder();
        int orderPol1 = this.getOrder();
        int[] coefficients;

        Polynomial higherOrderPol;
        Polynomial lowerOrderPol;

        if (orderPol1 >= orderPol2) {
            higherOrderPol = this;
            lowerOrderPol = pol2;
        } else {
            higherOrderPol = pol2;
            lowerOrderPol = this;
        }
        coefficients = new int[higherOrderPol.root.getExponent() + 1];

        Node currentNode1 = higherOrderPol.root;
        Node currentNode2 = lowerOrderPol.root;


        for (int i = 0; i < coefficients.length; i++) {
            if (currentNode1 != null && currentNode2 != null) {
                if (currentNode1.getExponent() == currentNode2.getExponent()) {
                    int exp = currentNode1.getExponent();
                    coefficients[exp] = currentNode1.getCoefficient() + currentNode2.getCoefficient();
                    currentNode1 = currentNode1.getNextNode();
                    currentNode2 = currentNode2.getNextNode();
                } else {
                    if (currentNode1.getExponent() > currentNode2.getExponent()) {
                        int exp = currentNode1.getExponent();
                        coefficients[exp] = currentNode1.getCoefficient();
                        currentNode1 = currentNode1.getNextNode();
                    } else {
                        int exp = currentNode2.getExponent();
                        coefficients[exp] = currentNode2.getCoefficient();
                        currentNode2 = currentNode2.getNextNode();
                    }
                }
            }else{
                if(currentNode1 == null && currentNode2 != null){
                    int exp = currentNode2.getExponent();
                    coefficients[exp] = currentNode2.getCoefficient();
                    currentNode2 = currentNode2.getNextNode();
                }else{
                    break;
                }
            }
        }
        return new Polynomial(coefficients);

    }

    public int getCoefficient(int exponent) {
        Node node = this.root;
        int coef = 0;
        for (int i = 1; i <= this.length; i++) {
            if (node.getExponent() == exponent) {
                coef = node.getCoefficient();
                return coef;
            } else {
                node = node.getNextNode();
            }
        }
        return coef;
    }


    public void setCoefficient(int exponent, int coef) {
        if(exponent < 0){
            return;
        }

        Node currentNode = this.root;
        Node prevNode = currentNode;

        for (int i = 1; i <= this.length; i++) {

            if (currentNode.getExponent() == exponent) {
                if (coef != 0) {
                    currentNode.setCoefficient(coef);
                } else {
                    removeNode(prevNode, currentNode);
                }
                break;
            } else {
                if (exponent > this.root.getExponent() || (exponent < prevNode.getExponent() && exponent > currentNode.getExponent()) || currentNode.getNextNode() == null) {
                    createNode(prevNode, currentNode, exponent, coef);
                    break;
                } else {
                    prevNode = currentNode;
                    currentNode = currentNode.getNextNode();
                }
            }
        }
    }


    private void createNode(Node prevNode, Node currentNode, int exp, int coef) {
        if (prevNode == currentNode) {
            Node node = new Node(coef, exp, currentNode);
            this.root = node;
        } else {
            if (currentNode.getNextNode() == null && currentNode.getExponent() > exp) {
                Node node = new Node(coef, exp, null);
                currentNode.setNextNode(node);
            } else {
                Node node = new Node(coef, exp, currentNode);
                prevNode.setNextNode(node);
            }

        }
        length++;
    }


    private void removeNode(Node prevNode, Node currentNode) {
        if (prevNode == currentNode) {
            this.root = currentNode.getNextNode();
        } else {
            prevNode.setNextNode(currentNode.getNextNode());
        }

        currentNode.setNextNode(null);
        this.length--;
    }

    public int getOrder() {
        return this.root.exponent;
    }

    public float valueOf(float x) {
        float value = 0;
        if (x == 0) {
            value = getCoefficient(0);
            return value;
        }

        Node currentNode = root;

        for (int i = 1; i <= this.length; i++) {
            int coefficient = currentNode.getCoefficient();
            int exponent = currentNode.getExponent();

            value += coefficient * Math.pow(x, exponent);
            currentNode = currentNode.getNextNode();
        }

        return value;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Polynomial pol = (Polynomial) o;
        if (pol.length != this.length) return false;

        boolean areEquals = true;
        Node currentNode1 = this.root;
        Node currentNode2 = pol.root;

        for (int i = 1; i <= this.length; i++) {
            if (currentNode1.equals(currentNode2)) {
                currentNode1 = currentNode1.getNextNode();
                currentNode2 = currentNode2.getNextNode();
                continue;
            } else {
                areEquals = false;
                return areEquals;
            }
        }
        return areEquals;
    }


    @Override
    public String toString() {
        StringBuilder pol = new StringBuilder("p(x) = ");
        Node node = root;
        for (int i = 1; i <= length; i++) {

            if (node.getCoefficient() > 0) {
                if (i != 1) {
                    if (node.getExponent() > 0) {
                        pol.append("+ " + node.getCoefficient() + "x^" + node.getExponent() + " ");
                    } else {
                        pol.append("+ " + node.getCoefficient());
                    }
                } else {
                    if (node.getExponent() > 0) {
                        pol.append(node.getCoefficient() + "x^" + node.getExponent() + " ");
                    } else {
                        pol.append(node.getCoefficient());
                    }
                }
            } else {
                if (node.getExponent() > 0) {
                    pol.append(node.getCoefficient() + "x^" + node.getExponent() + " ");
                } else {
                    pol.append(node.getCoefficient());
                }
            }
            node = node.getNextNode();

        }
        return pol.toString();
    }


    class Node {
        private int coefficient;
        private int exponent;
        private Node nextNode = null;

        public Node(int coefficient, int exponent, Node nextNode) {
            this.nextNode = nextNode;
            this.coefficient = coefficient;
            this.exponent = exponent;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return this.getCoefficient() == node.getCoefficient() &&
                    this.getExponent() == node.getExponent();
        }


        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getExponent() {
            return exponent;
        }

//        public void setExponent(int exponent) {
//            this.exponent = exponent;
//        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }


}
