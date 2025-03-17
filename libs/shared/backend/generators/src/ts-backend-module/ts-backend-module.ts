import { formatFiles, generateFiles, Tree } from '@nx/devkit';
import * as path from 'path';
import { TsBackendModuleGeneratorSchema } from './schema';
import { insertImport } from '@schematics/angular/utility/ast-utils';
import { InsertChange } from '@schematics/angular/utility/change';
import * as ts from 'typescript';
import { pluralize } from 'inflection';

export async function tsBackendModuleGenerator(
  tree: Tree,
  options: TsBackendModuleGeneratorSchema
) {
  options.fileNameP = pluralizeHyphenated(options.fileName);

  options.name = toNameCase(options.fileName);
  options.className = toClassNameCase(options.fileName);

  options.nameP = toNameCase(options.fileNameP);
  options.classNameP = toClassNameCase(options.fileNameP);

  options.projectAcronym = 'W';
  options.projectAcronymLowCase = 'w';

  const startRoot = `apps/w-n-backend/src/app`;
  const projectRoot = `apps/w-n-backend/src/app/${options.fileNameP}`;

  //where are the templates
  const templatePath = 'files/module';

  generateFiles(
    tree,
    path.join(__dirname, `${templatePath}/common`),
    projectRoot,
    options
  ); //project Root you can select where are the files. just  if you only want a specific file or root

  if (options.methods.includes('CREATE')) {
    generateFiles(
      tree,
      path.join(__dirname, `${templatePath}/methods/create`),
      projectRoot,
      options
    );
  }
  if (options.methods.includes('UPDATE')) {
    generateFiles(
      tree,
      path.join(__dirname, `${templatePath}/methods/update`),
      projectRoot,
      options
    );
  }
  if (options.methods.includes('FINDALL')) {
    generateFiles(
      tree,
      path.join(__dirname, `${templatePath}/methods/find-all`),
      projectRoot,
      options
    );
  }

  if (options.methods.includes('DELETE')) {
    generateFiles(
      tree,
      path.join(__dirname, `${templatePath}/methods/delete`),
      projectRoot,
      options
    );
  }

  if (options.methods.includes('FINDBYID')) {
    generateFiles(
      tree,
      path.join(__dirname, `${templatePath}/methods/find-by-id`),
      projectRoot,
      options
    );
  }

  // Update app.module.ts
  const modulePath = `${startRoot}/app.module.ts`;

  const sourceText = tree.read(modulePath)?.toString('utf-8');

  if (!sourceText) {
    throw new Error(`Module file not found: ${modulePath}`);
  }

  const sourceFile = ts.createSourceFile(
    modulePath,
    sourceText,
    ts.ScriptTarget.Latest,
    true
  );

  const moduleName = `${options.classNameP}Module`;
  const moduleImportPath = `./${options.fileNameP}/${options.fileNameP}.module`;

  // Insert the import statement
  const importChange = insertImport(
    sourceFile,
    modulePath,
    moduleName,
    moduleImportPath
  );
  let updatedSourceText = sourceText;
  if (importChange instanceof InsertChange) {
    updatedSourceText =
      updatedSourceText.slice(0, importChange.pos) +
      importChange.toAdd +
      updatedSourceText.slice(importChange.pos);
  }

  const updatedModuleText = insertIntoImportsArray(
    updatedSourceText,
    moduleName
  );

  // Write the updated content back to app.module.ts
  tree.write(modulePath, updatedModuleText);

  await formatFiles(tree);
}

export default tsBackendModuleGenerator;

// Input: user-icon Output: userIcon
function toNameCase(str: string): string {
  return str
    .toLowerCase() // Convert to lowercase to ensure consistency
    .split('-') // Split the string by the hyphen
    .map((word, index) => {
      if (index === 0) {
        return word; // Keep the first word lowercase
      }
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize the first letter of the other words
    })
    .join(''); // Join them back together
}

// Input: user-icon Output: UserIcon
function toClassNameCase(str: string): string {
  return str
    .toLowerCase() // Convert to lowercase to ensure consistency
    .split('-') // Split the string by the hyphen
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1)) // Capitalize the first letter of each word
    .join(''); // Join them back together
}

// Function to insert a module name into the imports array
function insertIntoImportsArray(sourceText, newImport) {
  if (sourceText.includes('imports: [')) {
    // Append provider if the providers array exists
    sourceText = sourceText.replace(
      'imports: [',
      `imports: [\n ${newImport}, `
    );
    return sourceText;
  }
  console.error('Error adding the import to the app.module.ts');
  return sourceText;
}

// Function to pluralize each word
function pluralizeHyphenated(input: string): string {
  return input
    .split('-') // Split the input string by hyphen
    .map((word) => pluralize(word)) // Pluralize each word
    .join('-'); // Rejoin the words with hyphens
}
