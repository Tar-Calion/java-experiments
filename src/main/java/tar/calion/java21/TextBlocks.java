package tar.calion.java21;

public class TextBlocks {

    public static void main(String[] args) {
        String block = """
                <html>
                    <body>
                        <p>Hello, World</p>
                    </body>
                </html>
                """;
        printBlock("block", block);

        var blockWithCR = """
                <html>\r
                    <body>\r
                        <p>Hello, World</p>\r
                    </body>\r
                </html>\r
                """;
        printBlock("blockWithCR", blockWithCR);

        var blockWithLF = """
                <html>\n
                    <body>\n
                        <p>Hello, World</p>\n
                    </body>\n
                </html>\n
                """;
        printBlock("blockWithLF", blockWithLF);

        var blockWithCRLF = """
                <html>\r\n
                    <body>\r\n
                        <p>Hello, World</p>\r\n
                    </body>\r\n
                </html>\r\n
                """;
        printBlock("blockWithCRLF", blockWithCRLF);

        var blockWithIndent = """    
                Line 1
                    Line 2
                Line 3
                    """;
        printBlock("blockWithIndent", blockWithIndent);

        var blockWithIndent2 = """
                                Line 1
                Line 2
                                Line 3
                                """;
        printBlock("blockWithIndent2", blockWithIndent2);

        var blockWithIndent3 = """
                Line 1
                Line 2
                Line 3
                Line 4""";
        printBlock("blockWithIndent3", blockWithIndent3);

        var blockWithIndent4 = """
                Line 1
                Line 2
                Line 3
                Line 4\
                """;
        printBlock("blockWithIndent4", blockWithIndent4);

        var blockWithIndent5 = """
                    Line 1
                    Line 2
                    Line 3
                    Line 4
                """;
        printBlock("blockWithIndent5", blockWithIndent5);

        var blockWithIndent6 = """
                    Line 1
                    Line 2
                    Line 3
                    Line 4\
                """;
        printBlock("blockWithIndent6", blockWithIndent6);

        var blockWithIndent7 = """
                Line 1

                     \s""";
        printBlock("blockWithIndent7", blockWithIndent7);

        var stringWithEscapes = "abc\sdef";
        System.out.println(stringWithEscapes);

        var blockWithTrailingSpaces = """
                Line 1   \s
                Line 2   
                """;
        printBlock("blockWithTrailingSpaces", blockWithTrailingSpaces);

        var blockNotIndented = """
                Line 1
                Line 2
                """;
        var indented = blockNotIndented.indent(4);
        printBlock("indented", indented);
    }

    private static void printBlock(String name, String block) {
        System.out.println(name + ":");
        block = block.replace("\r\n", "CRLF\r\n");
        block = block.replace("\n", "LF\n");
        block = block.replace("\r", "CR\r");
        System.out.println(block + "END");
    }
}
