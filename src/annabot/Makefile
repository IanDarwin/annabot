ANTLR_JAR=	$$HOME/lib/antlr-3.1.1.jar
SRCS=		AnnaBot.java antlr/*.java
BUILD=		../../build

try:	build
	java -classpath $(BUILD):$(ANTLR_JAR) annabot.AnnaBot ../../Minimal.claim

build:	antlr
	javac -d ${BUILD} -classpath $(BUILD):$(ANTLR_JAR) $(SRCS)

antlr:	antlr/Grammar.g antlr/TreeWalker.g
	java -classpath $(ANTLR_JAR) org.antlr.Tool -lib antlr antlr/Grammar.g antlr/TreeWalker.g

clean:
	@rm -f Grammar.tokens GrammarLexer.java GrammarParser.java TreeWalker.java TreeWalker.tokens
