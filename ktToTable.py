file = open('KotlinSupportLibrary_AppCompatActivity.kt')

print("|Function|Description|Arguments|Return")
print("|:---:|---|:---:|:---:")

for line in file:
    if 'fun ' in line:
        function = line.partition("AppCompatActivity.")[2].partition("(")[0]
        import re

        try:
            arguments = re.findall(r'\((.*?)\)', line)[0]
        except IndexError:
            arguments = ''
        try:
            rvalue = re.findall(r'(?<=\):)[^}]*(?={)', line)[0]
        except IndexError:
            rvalue = ''

        print("|{}|description|{}|{}|".format(function, arguments, rvalue))
