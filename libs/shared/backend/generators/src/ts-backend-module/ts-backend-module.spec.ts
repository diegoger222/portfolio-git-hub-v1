import { createTreeWithEmptyWorkspace } from '@nx/devkit/testing';
import { Tree } from '@nx/devkit';

import { tsBackendModuleGenerator } from './ts-backend-module';
import { TsBackendModuleGeneratorSchema } from './schema';

describe('ts-backend-module generator', () => {
  let tree: Tree;
  const options: TsBackendModuleGeneratorSchema = {
    name: 'testName',
    className: 'TestName',
    fileName: 'test-name',
    classNameP: 'TestsNames',
    fileNameP: 'tests-names',
    nameP: 'testsNames',
    methods: [],
    projectAcronym: 'W',
    projectAcronymLowCase: 'w',
  };

  beforeEach(() => {
    tree = createTreeWithEmptyWorkspace();
    tree.write(
      'apps/w-n-backend/src/app/app.module.ts',
      `
      import { Module } from '@nestjs/common';

      @Module({
        imports: [],
        controllers: [],
        providers: [],
      })
      export class AppModule {}
    `
    );
  });

  it('should run successfully', async () => {
    await tsBackendModuleGenerator(tree, options);
    // Verify that the module file was generated in the correct location
    const moduleFilePath =
      'apps/w-n-backend/src/app/tests-names/tests-names.module.ts';
    expect(tree.exists(moduleFilePath)).toBe(true);

    expect(tree).toBeDefined();
  });
});
