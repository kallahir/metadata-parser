import sys
from controller.JavaParser import JavaParser
from controller.SwiftParser import SwiftParser
from controller.KotlinParser import KotlinParser

METADATA_FILE = sys.argv[1]
LANGUAGE = sys.argv[2]

# LANGUAGES ====================================================================
class Language:
    KOTLIN = 'kotlin'
    JAVA = 'java'
    SWIFT = 'swift'

    def getParser(document):
        if (LANGUAGE == Language.KOTLIN):
            return KotlinParser(document)
        elif (LANGUAGE == Language.JAVA):
            return JavaParser(document)
        elif (LANGUAGE == Language.SWIFT):
            return SwiftParser(document)

def openFile():
    return


# MAIN =========================================================================
parser = Language.getParser(openFile)
parser.createFile()
