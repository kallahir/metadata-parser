import os, sys, re
from xml.dom import minidom

# CONSTANTS ====================================================================
OUTPUT_DIR = sys.argv[2] if len(sys.argv) > 2 else ''

# CLASSESS =====================================================================
class Language:
    JAVA = 'java'
    KOTLIN = 'kt'
    SWIFT = 'swift'

class Member:
    def __init__(self, element):
        self.visibility = element.attributes['visibility'].value
        self.type = element.attributes['type'].value
        self.name = element.attributes['name'].value

    def declareMethod(self, language):
        if (language == Language.JAVA):
            return "{} {} {};\n".format(self.visibility, self.type, self.name)
        elif (language == Language.KOTLIN):
            return "{} var {} : {} \n".format(self.visibility, self.name, self.type)
        elif (language == Language.SWIFT):
            return "{} var {} : {}\n".format(self.visibility, self.name, self.type)
        else:
            return "language not supported"

    def declareClass(self, language):
        if (language == Language.JAVA):
            return "{} {} {} {{\n".format(self.visibility, self.type, self.name)
        elif (language == Language.KOTLIN):
            return "class {} {{\n".format(self.name, self.type)
        elif (language == Language.SWIFT):
            return "{} class {} {{\n".format(self.visibility, self.name)
        else:
            return "language not supported"

# METHODS ======================================================================
def assembleClass (document, language):
    _class = Member(document.getElementsByTagName('entity')[0])

    if OUTPUT_DIR: filename = "{}/{}.{}".format(OUTPUT_DIR, _class.name, language)
    else: filename = "{}.{}".format(_class.name, language)

    editor = open (filename, 'w')
    editor.write(_class.declareClass(language))

    for m in document.getElementsByTagName('member'):
        member = Member(m)
        editor.write("\t{}".format(member.declareMethod(language)))

    editor.write("}")
    editor.close()

# MAIN =========================================================================
document = minidom.parse(sys.argv[1])
assembleClass(document, Language.JAVA)
assembleClass(document, Language.KOTLIN)
assembleClass(document, Language.SWIFT)
